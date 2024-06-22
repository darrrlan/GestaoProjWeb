/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.lock;

/**
 *
 * @author darla
 */
public class Lock {
    private boolean isLocked = false;

    public boolean isLocked() {
        return isLocked;
    }

    public synchronized void lock() {
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
    }
}

