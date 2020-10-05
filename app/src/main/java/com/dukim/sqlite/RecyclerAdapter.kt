package com.dukim.sqlite

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class RecyclerAdapter: Adapter<RecyclerAdapter.Hoder>() {
    var listData = mutableListOf<RoomMemo>()
    //SqliteHelper => RoomHelper 변경
    var helper:RoomHelper?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hoder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler,parent,false)
        return Hoder(view)
    }

    override fun onBindViewHolder(holder: Hoder, position: Int) {
        val RoomMemo = listData.get(position)
        holder.setRoomMemo(RoomMemo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Hoder(itemView: View): ViewHolder(itemView)
    {
        var mRoomMemo:RoomMemo?=null

        init{
            itemView.buttonDelete.setOnClickListener{
                helper?.roomMemoDao()?.delete(mRoomMemo!!)
                listData.remove(mRoomMemo)
                notifyDataSetChanged()
            }
        }

        fun setRoomMemo(RoomMemo: RoomMemo)
        {
            itemView.textNo.text = "${RoomMemo.no}"
            itemView.textContent.text = RoomMemo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")

            itemView.textDatetime.text = "${sdf.format(RoomMemo.datetime)}"

            this.mRoomMemo = RoomMemo
        }
    }
}

