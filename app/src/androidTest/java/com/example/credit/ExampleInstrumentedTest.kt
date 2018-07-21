package com.example.credit

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasErrorText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.credit.creditForm.CreditFormActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val creditCardNo = "4916571937914203"
    private val invalidCreditCardNo = "4916571937914202"
    private val expiryMonth = "02"
    private val invalidExpiryMonth = "22"
    private val expiryYear = "18"
    private val invalidExpiryYear = "15"
    private val cvv = "220"
    private val invalidCvv = "02"
    private val name = "Sanoop"
    private val invalidName = ""

    //Error messages
    private val creditCardError = "Invalid credit card format"
    private val dateError = "Invalid data format"
    private val cvvError = "Invalid field. The minimum number of characters 3"
    private val nameError = "This field can't be empty"

    @get:Rule
    var mActivityRule: ActivityTestRule<CreditFormActivity> = ActivityTestRule(
            CreditFormActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.credit", appContext.packageName)
    }

    @Test
    fun testValidations() {
        val appContext = InstrumentationRegistry.getTargetContext()
        // Enter wrong credit info and should show error
        onView(withId(R.id.edtCard)).perform(typeText(invalidCreditCardNo), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.edtCard)).check(matches(hasErrorText(creditCardError)))
        onView(withId(R.id.edtCard)).perform(clearText())
        onView(withId(R.id.edtCard)).perform(typeText(creditCardNo), closeSoftKeyboard())
        // Enter wrong month info and should show error
        onView(withId(R.id.edtMonth)).perform(typeText(invalidExpiryMonth), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.edtMonth)).check(matches(hasErrorText(dateError)))
        onView(withId(R.id.edtMonth)).perform(clearText())
        onView(withId(R.id.edtMonth)).perform(typeText(expiryMonth), closeSoftKeyboard())

        // Enter wrong year info and should show error
        onView(withId(R.id.edtYear)).perform(typeText(invalidExpiryYear), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.edtYear)).check(matches(hasErrorText(appContext.getString(R.string.year_error))))
        onView(withId(R.id.edtYear)).perform(clearText())
        onView(withId(R.id.edtYear)).perform(typeText(expiryYear), closeSoftKeyboard())

        // Enter wrong CVV info and should show error
        onView(withId(R.id.edtCvv)).perform(typeText(invalidCvv), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.edtCvv)).check(matches(hasErrorText(cvvError)))
        onView(withId(R.id.edtCvv)).perform(clearText())
        onView(withId(R.id.edtCvv)).perform(typeText(cvv), closeSoftKeyboard())

        // Enter wrong Name info and should show error
        onView(withId(R.id.edtName)).perform(typeText(invalidName), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.edtName)).check(matches(hasErrorText(nameError)))
        onView(withId(R.id.edtName)).perform(clearText())
        onView(withId(R.id.edtName)).perform(typeText(name), closeSoftKeyboard())

    }
}
