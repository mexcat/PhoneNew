
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cl.gencina.phonenew.data.local.PhoneDao
import cl.gencina.phonenew.data.local.PhoneDatabase
import cl.gencina.phonenew.data.local.PhoneEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

//test unitario
class RoomDatabaseTest {

        @get:Rule
        var instantExecutorRule = InstantTaskExecutorRule()

        private lateinit var phoneDao: PhoneDao
        private lateinit var db: PhoneDatabase

        @Before
        fun setUp() {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(context, PhoneDatabase::class.java).build()
            phoneDao = db.PhoneDao()
        }

        @After
        fun tearDown() {
            db.close()
        }

        @Test
        fun insertPhone_hapSipyCase_1element() = runBlocking {
            // Given
            val phoneData = PhoneEntity(123,"test", 99999, "image",null,null,null)

            // When
            phoneDao.insertBase(phoneData)

            // Then
            phoneDao.getAll().observeForever {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).isNotEmpty()
                Truth.assertThat(it).hasSize(1)
            }
        }


    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T


}