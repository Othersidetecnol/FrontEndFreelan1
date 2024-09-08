import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.frontendfreelan.databinding.ActivityTaskFormBinding
import com.example.frontendfreelan.ui.notifications.NotificationsViewModel

// TaskFormActivity.kt
class TaskFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskFormBinding
    private var taskPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val task = intent.getParcelableExtra<NotificationsViewModel.Task>("task")
        taskPosition = intent.getIntExtra("position", -1)

        task?.let {
            binding.inputName.setText(it.title)
            binding.inputDate.setText(it.date)
            binding.inputTime.setText(it.time)
            binding.inputDetails.setText(it.description)
            binding.inputValue.setText(it.value.toString())
        }

        binding.saveButton.setOnClickListener {
            val newTask = NotificationsViewModel.Task(
                binding.inputName.text.toString(),
                binding.inputDate.text.toString(),
                binding.inputTime.text.toString(),
                binding.inputDetails.text.toString(),
                binding.inputValue.text.toString().toDouble()
            )

            val resultIntent = Intent().apply {
                putExtra("task", newTask)
                putExtra("position", taskPosition)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
