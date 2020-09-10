package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;

import java.math.BigInteger;

public class ThreadPrimeFinder extends Thread{
    private  PrimesResultSet prs;
    private  int fin;
    private  int inicio;

    public ThreadPrimeFinder(int inicio, int fin, PrimesResultSet prs){
        this.inicio = inicio;
        this.fin = fin;
        this.prs = prs;
    }
    public void run(){
        System.out.println(inicio+" "+fin);

        MathUtilities mt=new MathUtilities();

        int itCount=0;

        BigInteger i= new BigInteger(String.valueOf(inicio));
        while (i.compareTo(new BigInteger(String.valueOf(fin)))<=0){
            itCount++;
            if (mt.isPrime(i)){
                System.out.println("meti uno");
                prs.addPrime(i);
            }

            i=i.add(BigInteger.ONE);
        }
    }
}
