package com.nepplus.listviewpractice_20210322.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.nepplus.listviewpractice_20210322.R
import com.nepplus.listviewpractice_20210322.datas.Student
import java.util.*
import kotlin.collections.ArrayList

class StudentAdapter(
    val mContext : Context,//화면을 갖는 변수
    val resId : Int,
    val mList : ArrayList<Student>) : ArrayAdapter<Student>(mContext, resId, mList) {

//한 칸 짜리 xml -> 코틀린 변수로 꺼내오도록 하는 클래스,xml 꺼내오는 변수, xml 모양을 끌어와준다. mContext -> 어떤 화면을 갖는 변수
    val inflater = LayoutInflater.from(mContext) //어떤 화면에서 쓸것인가

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//   기존의 결과를 버리고 ,새로운 결과를 직접 사용
//  position : 지금 그려주는 위치가 어디인지 알려준다
//  convertView : 재활용 할 요소가 있는지 -> 있으면 활용 , 없으면 새로 그려주자

      var tempRow = convertView

//        재활용 할 요소가 없나 -> 없으면 그려줘야함
//      화면에 xml을 뿌려줄 상황에만 코딩을 한 것이다.
        if(tempRow == null){
//      tempRow 내부가 비어있는 상황 -> 채워주자
            tempRow = inflater.inflate(R.layout.student_list_item, null)
        }

//        tempRow에는 무조건 깡통이 나가는 것을 막아준다.
        val row = tempRow!!
//완성된 row가 화면에 뿌려준다

//        실제 데이터 UI요소에 반영
//-> mList안에 들어있다 -> mList안에서 몇번째 것을 꺼낼건지 찾을것

        val studentData = mList[position]

// 뿌려질 요소를 가져오자 -> row변수 안에 있는 텍스트뷰등을 꺼내오자
        val nameTxt = row.findViewById<TextView>(R.id.nameTxt)
        val ageTxt = row.findViewById<TextView>(R.id.ageTxt)
        val addressTxt = row.findViewById<TextView>(R.id.addressTxt)

//        실제 데이터 UI 반영

        nameTxt.text = studentData.name
        addressTxt.text = studentData.address

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val koreanAge = 2021 - studentData.birthYear + 1

         ageTxt.text = "(${koreanAge}세)"



        return row

    }

}