package com.gibsoncodes.movieapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

// enables us to have control of the coroutines scope
@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule, TestCoroutineScope by TestCoroutineScope() {
    private val testCoroutinesDispatcher = TestCoroutineDispatcher()
    override fun apply(base: Statement?, description: Description?): Statement {
        // run the action
        val obj = object : Statement() {
            override fun evaluate() {
                // main dispatchers
                Dispatchers.setMain(testCoroutinesDispatcher)
                evaluate()
                Dispatchers.resetMain() // tear down dependencies and switch back to the original dispatcher
            }
        }
        return obj
    }
}