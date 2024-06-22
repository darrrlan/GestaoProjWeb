/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.lock;

/**
 *
 * @author darla
 */
import br.data.lock.Lock;
import java.util.HashMap;
import java.util.Map;

public class LockManager {

    private Map<Integer, Lock> locks = new HashMap<>();

    public synchronized void lock(int resourceId) throws InterruptedException {
        Lock lock = getLock(resourceId);
        while (lock.isLocked()) {
            wait(); // Espera enquanto o recurso está travado
        }
        lock.lock(); // Adquire o travamento
    }

    public synchronized void unlock(int resourceId) {
        Lock lock = getLock(resourceId);
        lock.unlock(); // Libera o travamento
        notifyAll(); // Notifica todas as threads esperando pelo travamento
    }

    private Lock getLock(int resourceId) {
        return locks.computeIfAbsent(resourceId, k -> new Lock());
    }
}

