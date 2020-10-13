package dev.fummicc1.sample.sampleofroomapp.screens.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.fummicc1.sample.sampleofroomapp.R
import dev.fummicc1.sample.sampleofroomapp.entity.Todo
import kotlinx.android.synthetic.main.todo_recyclerview_item.view.*
import java.util.zip.Inflater

class TodoRecyclerViewAdapter(context: Context): RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var todos: List<Todo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.todo_recyclerview_item, parent, false)
        return ViewHolder((view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoTaskTextView.text = todo.task
    }

    override fun getItemCount(): Int = todos.size

    fun updateTodos(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val todoTaskTextView: TextView = view.findViewById<TextView>(R.id.todo_task_text_view)
    }
}