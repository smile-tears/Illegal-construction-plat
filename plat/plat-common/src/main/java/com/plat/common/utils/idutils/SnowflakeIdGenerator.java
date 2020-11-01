package com.plat.common.utils.idutils;

import java.math.BigInteger;

/**
 * ClassName: SnowflakeIdGenerator <br/>
 * @Author: Cui.xx  <br/>
 * Date: 2019/4/12 17:52 <br/>
 * Version: 1.0 <br/>
 * Description:  <br/>
 */
public class SnowflakeIdGenerator implements IdGenerator {
    private final long workerId;
    private static final long twepoch = 1361753741828L;
    private long sequence = 0L;
    private static final long workerIdBits = 4L;
    public static final long maxWorkerId = 15L;
    private static final long sequenceBits = 10L;
    private static final long workerIdShift = 10L;
    private static final long timestampLeftShift = 14L;
    public static final long sequenceMask = 1023L;
    private long lastTimestamp = -1L;

    @Override
    public long nextId() {
        long timestamp = this.timeGen();
        if(this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1L & 1023L;
            if(this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        if(timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", new Object[]{Long.valueOf(this.lastTimestamp - timestamp)}));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = timestamp - 1361753741828L << 14 | this.workerId << 10 | this.sequence;
        return nextId;
    }

    public SnowflakeIdGenerator(long workerId) {
        if(workerId <= 15L && workerId >= 0L) {
            this.workerId = workerId;
        } else {
            throw new IllegalArgumentException(String.format("worker Id can\'t be greater than %d or less than 0", new Object[]{Long.valueOf(15L)}));
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
            ;
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public String nextId(int addPos) {
        Long id = Long.valueOf(this.nextId());
        BigInteger bigInteger = new BigInteger(id.toString());
        bigInteger = bigInteger.multiply(BigInteger.valueOf((long)Math.pow(10.0D, (double)addPos))).add(BigInteger.valueOf((long)((Math.random() * 9.0D + 1.0D) * Math.pow(10.0D, (double)addPos))));
        return bigInteger.toString();
    }
}
