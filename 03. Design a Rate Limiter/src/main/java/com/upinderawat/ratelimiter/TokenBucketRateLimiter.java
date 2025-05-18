package com.upinderawat.ratelimiter;

public class TokenBucketRateLimiter {
    private final long capacity;
    private final long refillRate;
    private long tokens;
    private long lastRefillTimeStamp;

    public TokenBucketRateLimiter(long capacity) {
        this.capacity = capacity;
        this.refillRate = capacity;
        this.tokens = capacity;
        this.lastRefillTimeStamp = System.currentTimeMillis();
    }
    //lazy load the incoming tokens
    public synchronized boolean allowRequest(){
        refill();
        if(tokens > 0){
            tokens--;
            return true;
        }
        return false;
    }
    private void refill(){
        long now = System.currentTimeMillis();
        long tokensToAdd = (now - lastRefillTimeStamp)*refillRate/1000;
        if(tokensToAdd > 0){
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTimeStamp = now;
        }
    }
}
