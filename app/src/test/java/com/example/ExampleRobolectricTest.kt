package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.repository.MasterkeyRepository
import com.example.ui.navigation.NavDestination
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("UPSC Masterkey", appName)
  }

  @Test
  fun `verify navigation destinations match the 4 requested tabs`() {
    val items = NavDestination.items
    assertEquals(4, items.size)
    assertEquals("Syllabus", items[0].title)
    assertEquals("PYQs", items[1].title)
    assertEquals("Notes", items[2].title)
    assertEquals("Current Affairs", items[3].title)
  }

  @Test
  fun `verify Masterkey repository contains structured data for all pillars`() {
    val repo = MasterkeyRepository()
    assertTrue(repo.syllabusItems.value.isNotEmpty())
    assertTrue(repo.pyqItems.value.isNotEmpty())
    assertTrue(repo.noteItems.value.isNotEmpty())
    assertTrue(repo.currentAffairsItems.value.isNotEmpty())

    // Test toggle completion
    val initialItem = repo.syllabusItems.value.first()
    val initialCompleted = initialItem.isCompleted
    repo.toggleSyllabusCompletion(initialItem.id)
    assertEquals(!initialCompleted, repo.syllabusItems.value.first().isCompleted)
  }

  @Test
  fun `launch MainActivity successfully without crash`() {
    val activityController = Robolectric.buildActivity(MainActivity::class.java).setup()
    val activity = activityController.get()
    assertNotNull(activity)
  }
}
