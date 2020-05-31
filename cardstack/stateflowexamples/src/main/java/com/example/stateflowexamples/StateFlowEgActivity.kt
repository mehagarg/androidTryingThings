package com.example.stateflowexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_state_flow_eg.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class StateFlowEgActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow_eg)

        usingLiveData()
        usingStateFlow()
    }

    private fun usingLiveData() {
        val model: StateFlowEgViewModel by viewModels()
        model.getCounterValue().observe(this, Observer<Int> { value ->
            // update UI
            stateflowCounterUsingLiveData.text = value.toString()
        })
    }

    private fun usingStateFlow() {
        val viewModel: StateFlowCounterToReplaceAboveLiveData by viewModels()
        initCountObserver(viewModel)
        initView(viewModel)
    }

    private fun initCountObserver(viewModel: StateFlowCounterToReplaceAboveLiveData) {
        lifecycleScope.launch {
            viewModel.countState.collect {
                stateflowCounterUsingStateFlow.text = it.toString()
            }
        }
    }

    private fun initView(viewModel: StateFlowCounterToReplaceAboveLiveData) {
        incrementCount.setOnClickListener {
            incrementCounter(viewModel)
        }
        decrementCount.setOnClickListener {
            decrementCounter(viewModel)
        }
    }

    private fun incrementCounter(viewModel: StateFlowCounterToReplaceAboveLiveData) {
        viewModel.incrementCount()
    }

    private fun decrementCounter(viewModel: StateFlowCounterToReplaceAboveLiveData) {
        viewModel.decrementCount()
    }
}
