/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.model;

/**
 *
 * @author emiliano
 */

public class Foo<K>
{
    final private int count;
    final private K key;

    Foo(Builder<K> b)
    {
        this.count = b.count;
        this.key = b.key;
    }

    public static class Builder<K>
    {
        int count;
        K key;

        public Builder(Integer count) { this.count = count;}
        //static public <K2> Builder<K2> start() { return new Builder<K2>(8); }
        public Builder<K> setCount(int count) { this.count = count; return this; }
        public Builder<K> setKey(K key) { this.key = key; return this; }
        public Foo<K> build() { return new Foo(this); }
    }

    public static void main(String[] args)
    {
        //Foo<Integer>.Builder<Integer>
       
        //Foo<Integer>.Builder<Integer>(8)
        
        //Foo<Bar> foo1 = Foo.Builder.start().setCount(1).setKey(bar).build();
        // Type mismatch: cannot convert from Foo<Object> to Foo<Bar>

        //Foo<Bar> foo2 = Foo.Builder<Bar>.start().setCount(1).setKey(bar).build();
        // Multiple markers at this line
        // - Bar cannot be resolved
        // - Foo.Builder cannot be resolved
        // - Syntax error on token ".", delete this token
        // - The method start() is undefined for the type Foo<K>
        // - Duplicate local variable fooType mismatch: cannot convert from Foo<Object> to Foo<Bar>

        //Foo<Bar> foo3 = Foo<Bar>.Builder.start().setCount(1).setKey(bar).build();
        // Multiple markers at this line
        // - Foo cannot be resolved
        // - Syntax error on token ".", delete this token
        // - Bar cannot be resolved     
    }
}