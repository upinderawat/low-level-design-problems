package com.upinderawat.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenBucketRateLimiterMain {
    public static void main(String[] args){
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(5);
        Runnable r = ()->{
            if(tokenBucketRateLimiter.allowRequest()){
                System.out.println("Request allowed");
            }
            else{
                System.out.println("Request denied");
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<10; i++){
            executorService.submit(r);
        }
        try{
            executorService.shutdown();
        }
        finally {
            executorService.shutdownNow();
        }

    }
}
