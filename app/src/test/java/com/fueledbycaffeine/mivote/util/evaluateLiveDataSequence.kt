package com.fueledbycaffeine.mivote.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Assert

inline fun<reified T : Any> evaluateLiveDataSequence(liveData: LiveData<T>, evaluators: List<(T) -> Unit>, block: () -> Unit) {
  val observer = mockk<Observer<T>>()
  val slot = slot<T>()
  val list = arrayListOf<T>()

  every { observer.onChanged(capture(slot)) } answers { list.add(slot.captured) }
  liveData.observeForever(observer)

  block()

  Assert.assertEquals("LiveData did not emit expected number of times.", evaluators.size, list.size)

  list.forEachIndexed { index, value ->
    evaluators[index](value)
  }
}
