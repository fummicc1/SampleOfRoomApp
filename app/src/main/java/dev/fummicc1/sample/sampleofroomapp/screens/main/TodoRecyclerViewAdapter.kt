package dev.fummicc1.sample.sampleofroomapp.screens.main

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.fummicc1.sample.sampleofroomapp.R
import dev.fummicc1.sample.sampleofroomapp.entity.Todo
import dev.fummicc1.sample.sampleofroomapp.generated.callback.OnClickListener

class TodoRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var todos: List<Todo> = emptyList()

    lateinit var clickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.todo_recyclerview_item, parent, false)
        return ViewHolder((view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoTaskTextView.text = todo.task
        todo.imageResourceURL?.let {
            val uri = Uri.parse(it)
            uri?.let {
                val inputStream = context.contentResolver.openInputStream(it)
                inputStream?.let {
                    val bitmap = BitmapFactory.decodeStream(it)
                    holder.todoImageView.setImageBitmap(bitmap)
                }
            }
        }

        holder.view.setOnClickListener {
            clickListener.onItemClick(it, position, todos[position])
        }
    }

    override fun getItemCount(): Int = todos.size

    fun updateTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val todoTaskTextView: TextView = view.findViewById(R.id.todoTaskTextView)
        val todoImageView: ImageView = view.findViewById(R.id.todoItemImageView)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int, todo: Todo)
    }
}