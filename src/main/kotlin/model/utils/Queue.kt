package model.utils

import java.util.*

class Queue<E>(max: Int) {
    private val q = Array<Any?>(max){ null } as Array<E?>
    private var head = 0
    private var tail = 0
    private var count = 0

    fun isEmpty() = count == 0

    fun enqueue(item: E): E? =
        if (count == q.size) null
        else {
            q[tail] = item
            tail = (tail + 1) % q.size
            count++
            item
        }

    fun dequeue(): E? =
        if (isEmpty()) throw EmptyStackException()
        else {
            val item = q[head]
            head = (head + 1) % q.size
            count--
            item
        }

    fun peek(): E? =
        if (isEmpty()) throw EmptyStackException()
        else q[head]
}