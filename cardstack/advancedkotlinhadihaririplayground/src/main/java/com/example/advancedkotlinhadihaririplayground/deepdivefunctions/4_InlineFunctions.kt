package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

import java.lang.Exception

/**
// when we talk about inter-operability between Java and Kotlin,
// we mentioned about a Lambda expression in Kotlin gives way to Anonymous Func in Java6/7
// This adds its overhead, so if Lambda expression has a closure ie. referencing a variable from outer fun
// It will cause for an instance to be created therefore adding more memory overhead.

// All the lambda calls impact the call stack. I have a fun that calls another fun that can call another fun

// how do we optimize it. It has performance impact. Minimize by using InlineFunctions
 */

fun operation(op: ()-> Unit){
    println("Before calling op")
    op()
    println("After calling op")
}


inline fun operationInlined(op: ()-> Unit){
    println("Before calling op")
    op() // calling that fun // execute this fun
    println("After calling op")
}


// If you have exceptions, dont make fun inline.
fun operationInlinedException(op: ()-> Unit){
    println("Before calling op")
    op() // calling that fun // execute this fun
    throw Exception("A Message ") /** dont inline here as the call stack will reference the wrong line here */
    // if you keep fun inlined and there is an exception
    // on call stack, if you click on inline - inline fun body or inline fun call site.
    println("After calling op")
}

inline fun operationNoInline(noinline op: () -> Unit) {
    // dont inline this lambda
    // single lambda passing here, and you are saying dont inline, what are you trying to say
    // in scenarios where I have multiple lamdbad params, I can choose to say no-inline some params
}

// If I store the fun anywhere, you cannot inline those fun.
fun tryingToInline(op: () -> Unit){
    // assign to value of the op lamda to the reference.
    val reference = op /** Illegal usage of inline parameter */
    println("Assigned value")
    op() // call the fun and add inline modifier.
    // we cannot inline every fun
    // store ref to the fun, if I inline.
    // noinline, why we dont
    // calling lamdbads being executed. If you want to do more, you cannot do that

}

inline fun operationNoInlineMultipleLambdas(noinline op: () -> Unit, op2:()->Unit) {
    // dont inline this lambda
    // single lambda passing here, and you are saying dont inline, what are you trying to say
    // in scenarios where I have multiple lamdbad params, I can choose to say no-inline some params
    // now warning goes
}

fun anotherFunction(){
    operationInlined {
        println(
            "This is the actual op inlined function"
        )
    } // Check byte code below Inlined one
}

inline fun noOp(x: Int) {
    // If you try to add inline fun to a fun that does not need to be inlined, IDE is going to complain about it.
    // Inline fun is useful when you are passing lambdas.
//    works best with lambda params
}

// Now there is another modifier call `noInline`, it will not inline

fun main(){

    operation {
        println(
            "This is the actual call function"
        )
    } // Check byte code below Non Inline
    operationInlined {
        println(
            "This is the actual call function"
        )
    } // Check byte code below Inlined one

    // Notice the difference between inlined version and not-inlined version. Kotlin copies the entire inline operationInlined code to where I am invoking that call
    // It also inlines the actual code from the fun that I am passing.
    // what does it mean?
    /**
     * Inline basically takes a HOF and inline it. Copy and Paste contents to where it is called. Flattening it out. This provides with optimizations.
     * Anonymous classes & call stacks are avoided.
     *
     * It comes with a side-effect of copying and pasting code. Now take another fun
     */
}

/***  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^***/

// Not Inlined function in Java
/**
@Metadata(
    mv = {1, 1, 16},
    bv = {1, 0, 3},
    k = 2,
    d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0014\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¨\u0006\u0005"},
    d2 = {"main", "", "operation", "op", "Lkotlin/Function0;", "advancedkotlinhadihaririplayground_debug"}
)
public final class _4_InlineFunctionsKt {
    public static final void operation(@NotNull Function0 op) {
        Intrinsics.checkParameterIsNotNull(op, "op");
        String var1 = "Before calling op";
        boolean var2 = false;
        System.out.println(var1);
        op.invoke();
        var1 = "After calling op";
        var2 = false;
        System.out.println(var1);
    }

    public static final void main() {
        operation((Function0)null.INSTANCE);
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        main();
    }
}*/

/***  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^*  ^^^^^^^^^^^***/
// Inlined function in Java
/**
package com.example.advancedkotlinhadihaririplayground;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
    mv = {1, 1, 16},
    bv = {1, 0, 3},
    k = 2,
    d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0017\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¨\u0006\u0005"},
    d2 = {"main", "", "operationInlined", "op", "Lkotlin/Function0;", "advancedkotlinhadihaririplayground_debug"}
)
public final class _4_InlineFunctionsKt {
    public static final void operationInlined(@NotNull Function0 op) {
        int $i$f$operationInlined = 0;
        Intrinsics.checkParameterIsNotNull(op, "op");
        String var2 = "Before calling op";
        boolean var3 = false;
        System.out.println(var2);
        op.invoke();
        var2 = "After calling op";
        var3 = false;
        System.out.println(var2);
    }

    public static final void main() {
        int $i$f$operationInlined = false;
        String var1 = "Before calling op";
        boolean var2 = false;
        System.out.println(var1);
        int var3 = false;
        String var4 = "This is the actual call function";
        boolean var5 = false;
        System.out.println(var4);
        var1 = "After calling op";
        var2 = false;
        System.out.println(var1);
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        main();
    }
}
*/


// Another function decompiled code

/**
package com.example.advancedkotlinhadihaririplayground;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
    mv = {1, 1, 16},
    bv = {1, 0, 3},
    k = 2,
    d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0006\u0010\u0002\u001a\u00020\u0001\u001a\u0014\u0010\u0003\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u001a\u0017\u0010\u0006\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b¨\u0006\u0007"},
    d2 = {"anotherFunction", "", "main", "operation", "op", "Lkotlin/Function0;", "operationInlined", "advancedkotlinhadihaririplayground_debug"}
)
public final class _4_InlineFunctionsKt {
    public static final void operation(@NotNull Function0 op) {
        Intrinsics.checkParameterIsNotNull(op, "op");
        String var1 = "Before calling op";
        boolean var2 = false;
        System.out.println(var1);
        op.invoke();
        var1 = "After calling op";
        var2 = false;
        System.out.println(var1);
    }

    public static final void operationInlined(@NotNull Function0 op) {
        int $i$f$operationInlined = 0;
        Intrinsics.checkParameterIsNotNull(op, "op");
        String var2 = "Before calling op";
        boolean var3 = false;
        System.out.println(var2);
        op.invoke();
        var2 = "After calling op";
        var3 = false;
        System.out.println(var2);
    }

    public static final void anotherFunction() {
        int $i$f$operationInlined = false;
        String var1 = "Before calling op";
        boolean var2 = false;
        System.out.println(var1);
        int var3 = false;
        String var4 = "This is the actual op inlined function";
        boolean var5 = false;
        System.out.println(var4);
        var1 = "After calling op";
        var2 = false;
        System.out.println(var1);
    }

    public static final void main() {
        operation((Function0)null.INSTANCE);
        int $i$f$operationInlined = false;
        String var1 = "Before calling op";
        boolean var2 = false;
        System.out.println(var1);
        int var3 = false;
        String var4 = "This is the actual call function";
        boolean var5 = false;
        System.out.println(var4);
        var1 = "After calling op";
        var2 = false;
        System.out.println(var1);
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        main();
    }
}*/
