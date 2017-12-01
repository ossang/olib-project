# Olib-project

## olib-maker
 - java 8
 - velocity 1.7
 - zip4j 1.3.2
 
## olib-rss
 - java 8
 - spring boot 1.5.9
 - rome 1.0
 - json-simple
 - h2
 
## olib-threadpool
###example
```java
public static void main(String[] args) {
		
		String poolName = "test-pool";
		int maxThreadSize = 2;
		OlibThreadPoolService service = new OlibThreadPoolService(poolName, maxThreadSize);

		String key = "testThread";
		int threadCount = 5;
		for(int i=0; i<threadCount; i++){
			TestThread thread = new TestThread();
			service.start(thread , key+i);
		}
	}
```


```java
public class TestThread extends Thread{
	public TestThread(){}
	
	public void run(){
		System.out.println("start : "+this.currentThread().getName());
		try {Thread.sleep(5000);} catch (InterruptedException e) {}
		System.out.println("end : "+this.currentThread().getName());
	}
}
```

### console result
```java
start : test-pool-testThread0-0
start : test-pool-testThread1-0
end : test-pool-testThread0-0
end : test-pool-testThread1-0
start : test-pool-testThread2-0
start : test-pool-testThread3-0
end : test-pool-testThread2-0
end : test-pool-testThread3-0
start : test-pool-testThread4-0
end : test-pool-testThread4-0
```
 