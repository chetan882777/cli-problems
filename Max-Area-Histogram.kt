
import java.util.*

fun ps(input: Array<Int>): IntArray {
    val result = IntArray(input.size)
    val stack : Stack<Int> = Stack()

    stack.push(0)
    result[0] = -1

    for (i in input.indices){
        if(i == 0) continue
        while(!stack.empty() && input[stack.peek()] > input[i]){
            stack.pop()
        }
        result[i] = if(stack.empty()) -1 else stack.peek()
        stack.push(i)
    }
    return result
}

fun ns(input: Array<Int>): IntArray {
    val result = IntArray(input.size)
    val stack : Stack<Int> = Stack()

    stack.push(input.size -1)
    result[input.size - 1] = 7

    for (i in input.indices){
        if(input.size - i == 0) continue
        while(!stack.empty() && input[stack.peek()] > input[input.size - i -1]){
            stack.pop()
        }
        result[input.size - i -1] = if(stack.empty()) 7 else stack.peek()
        stack.push(input.size - i -1)
    }
    return result
}


fun maxArea(input: Array<Int>) : Int {
    val ns = ns(input)
    val ps = ps(input)

    var area : Int = 0

    for(i in input.indices){
        val t = input[i] + input[i] * (i - ps[i] - 1) + input[i] * (ns[i] - i - 1)
        if(t> area){
            area = t;
        }
    }

    return area;
}

fun main(){

    // histogram input array
    val input: Array<Int> = arrayOf(6, 2, 5, 4, 1 ,5, 6)
    println(maxArea(input))
}
