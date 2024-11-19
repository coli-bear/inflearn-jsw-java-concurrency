
# 스레드 예외처리 

스레드는 기본적으로 예외를 발생할 수 없기 때문에 `run()` 메서드 내부에서 예외처리를 해야한다. 따라서 `RuntimeException` 타입의 예외가 발생하여도 스레드 외부에서 예외의 캐치가 불가능하고 사라지게 된다.

이러한 스레드의 비정상 종료나 특정 예외를 스레드 외부에서 캐치하기 위해 자바에서는 `UncaughtExceptionHandler` 인터페이스를 제공한다.

## Thread.UncaughtExceptionHandler

캐치 되지 않은 예외에 의해 스레드가 종료되었을 때 호출되는 핸들러 인터페이스로 어떤 원인으로 인해 대상 스레드가 종료되었는지에 대해 파악이 가능하다. 

자바 내부에 아래와 같이 핸들러 인터페이스가 존재하며, 현재 스레드와 예외 정보를 전달하여 처리가 가능하다. 

```java
public class Thread implements Runnable {
	@FunctionalInterface  
	public interface UncaughtExceptionHandler {  
	    /**  
	     * Method invoked when the given thread terminates due to the     * given uncaught exception.     * <p>Any exception thrown by this method will be ignored by the  
	     * Java Virtual Machine.     * @param t the thread  
	     * @param e the exception  
	     */    void uncaughtException(Thread t, Throwable e);  
	}
}
```

## API 

### Thread.setDefaultUncaughtExceptionHandler

```java
public class Thread implements Runnable {
	// null unless explicitly set  
	private volatile UncaughtExceptionHandler uncaughtExceptionHandler;  
	  
	// null unless explicitly set  
	private static volatile UncaughtExceptionHandler defaultUncaughtExceptionHandler;
	public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) {  
	    SecurityManager sm = System.getSecurityManager();  
	    if (sm != null) {  
	        sm.checkPermission(  
	            new RuntimePermission("setDefaultUncaughtExceptionHandler")  
	                );  
	    }  
	  
	     defaultUncaughtExceptionHandler = eh;  
	}
	
	public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {  
	    checkAccess();  
	    uncaughtExceptionHandler = eh;  
	}

}
```

`@FunctionalInterface` 인 `UncaughtExceptionHandler` 를 전달받아 핸들러를 등록한다. 

정적 메서드와 인스턴스 메서드 두 가지 방법을 설정 방법을 제공한다. 

### Thread.getDefaultUncaughtExceptionHandler()

```java
public class Thread implements Runnable {
	public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler(){ 
	    return defaultUncaughtExceptionHandler;  
	}

	public UncaughtExceptionHandler getUncaughtExceptionHandler() {  
	    return uncaughtExceptionHandler != null ?  
	        uncaughtExceptionHandler : group;  
	}
}

```

`Thread#dispatchUncaughtException` 에서 호출되어 처리하게 된다.

## Sample Code

```java
public class UncaughtExceptionHandlerBase {  
    public static void main(String[] args) {  
        Thread.UncaughtExceptionHandler handler1 = (t, e) -> {  
            System.out.println("Thread name = " + t.getName());  
            System.out.println("Uncaught exception: " + e);  
        };  
        Thread.setDefaultUncaughtExceptionHandler(handler1);  
  
        new Thread(() -> {  
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());  
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");  
        }).start();  
  
        new Thread(() -> {  
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());  
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");  
        }).start();  
  
  
        Thread thread1 = new Thread(() -> {  
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());  
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");  
        });  
        thread1.setUncaughtExceptionHandler((t, e) -> {  
            System.out.println(">> Thread 1 Uncaught exception: " + e);  
        });  
  
        Thread thread2 = new Thread(() -> {  
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());  
            throw new RuntimeException(Thread.currentThread().getName() + " throws exception");  
        });  
        thread1.setUncaughtExceptionHandler((t, e) -> {  
            System.out.println(">> Thread 2 Uncaught exception: " + e);  
        });  
  
        thread1.start();  
        thread2.start();  
    }  
}
```

# 스레드 중지

자바에서는 무한 반복이나 지속적인 실행 중에 있는 스레드를 중지 하거나 종료할 수 있는 API 를 더이상 사용할 수 없다. 
단, 플래그 변수 또는 인터럽트를 활용해서 구현이 가능하다. 

## Flag Variable 

플래그의 값이 어떤 조건에 만족한 경우 스레드의 실행을 중지하는 방식이다. 단, 동시성 문제로 Atomic 변수나 volatile 키워드를 사용해야 한다. 


```Java
public class FlagThreadStopExample {  

	// 이 경우에는 스레드 1이 정상종료 되지 않음 
    // private static boolean running = true;  
    // 아래 volatile 키워드를 이용해 정의한 경우 정상 종료 됨 
    private static volatile boolean running = true;
  
    public static void main(String[] args) {  

        new Thread(() -> {  
            int count = 0;  
            while (running) {  
		        // 만약 volatile 키워드를 사용하지 않고 정상종료하기 위해서는 아래 코드를 추가한다. 
		        try {
			        Thread.sleep(1)
		        } catch(InterruptedException e) {
			        throw new RuntimeException e
		        }
                count++;  
            }  
            System.out.println("Thread 1 finished. Count = " + count);  
        }).start();  
  
        new Thread(() -> {  
            try {  
                Thread.sleep(500);  
            } catch (InterruptedException e) {  
                throw new RuntimeException(e);  
            }  
            System.out.println("Thread 2 finished.");  
            running = false; // 먼저 캐시에 저장되고 나중에 메인 메모리에 저장(OS가 언제 쓸지 모름)  
        }).start();  
    }  
}
```

이 현상은 각각의 CPU 의 각 스레드는 메모리보다 캐시메모리에서 데이터를 가져와 연산하는것이 빠르다. 

CPU는 더 빠른 성능을 위해서 메모리에 저장하기 전에 캐시메모리에 데이터를 임시로 저장하게 된다.(이후 CPU에 의해서 메모리에 저장되게 되는데 시점은 CPU가 결정한다.) 이때 `running` 의 값을 Thread#1 과 Thread#2 의 Context 에 저장하게 된다. 

| Thread   | running |
| -------- | ------- |
| Thread#1 | true    |
| Thread#2 | false   |

이때 Thread#2 에서 `running=false` 로 변경하게 되면 Thread#2의 Context 에 반영이 되고 Thread#1 에는 반영이 되지 않아 Thread#1 의 Context 에서는 `running=true` 로 인식하여 Thread#1 이 종료되지 않는다. 

하지만, Thread#1 에서 sleep() 이 발상하게 되면 Thread#2로 Context Switching 이 발생할하게 되고 캐시메모리를 비워야 한다. 이때 캐시메모리의 저장되어있는 정보를 메모리에 저장하게 된다. Thread#1 이 다시 문맥정보를 확인할때 캐시메모리에는 Context 가 없기 때문에 메모리로부터 다시 데이터를 불러와 처리하게 된다. 

> 즉, 스레드가 어떤 공유된 데이터를 참조하는것이 아닌 스레드가 각각 갖고 있는 Context 가 캐시되어 활용된다. 이것을 같은 메모리의 데이터를 참조하기 위한 키워드가 `volatile` 이다.

이해를 돕기위해 그림을 첨부해봤다.

#그림 삽입#

## Atomic 

데이터의 원자성을 보장하기 위해(동시성 문제를 해결하기 위해) 제공되는 코드이다. 참고만 하자(나중에 다시 나옴)

```java
public class AtomicThreadStopExample {  
    private AtomicBoolean running = new AtomicBoolean(true);  
    public static void main(String[] args) {  
        new AtomicThreadStopExample().start();  
    }  
  
    private void start() {  
        new Thread(() -> {  
            int count = 0;  
            while (running.get()) {  
                count++;  
            }  
            System.out.println("Thread 1 finished. Count = " + count);  
        }).start();  
  
        new Thread(() -> {  
            try {  
                Thread.sleep(500);  
            } catch (InterruptedException e) {  
                throw new RuntimeException(e);  
            }  
            System.out.println("Thread 2 finished.");  
            running.set(false);  
        }).start();  
    }  
}
```

# 사용자 스레드 vs 데몬 스레드

# ThreadGroup

# ThreadLocal