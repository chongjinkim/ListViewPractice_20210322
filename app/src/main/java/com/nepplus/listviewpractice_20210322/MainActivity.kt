package com.nepplus.listviewpractice_20210322

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nepplus.listviewpractice_20210322.adpaters.StudentAdapter
import com.nepplus.listviewpractice_20210322.datas.Student
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//학생 목록을 담아줄 ArrayList를 추가한다.

    val mStudentList = ArrayList<Student>()

//xml + 데이터 를 조합해서 뿌려주는 adapter 변수추가
// 변수는 미리 만들고 싶을떄, 대입은 나중에
    lateinit var mAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//학생 목록을 실제로 추가한다.

     mStudentList.add(Student("마르키시오", 1986, "이탈리아 밀라노"))
     mStudentList.add(Student("보누치", 1987, "이탈리아 토리노"))
     mStudentList.add(Student("데실리오", 1992, "이탈리아 피렌체"))
     mStudentList.add(Student("말디니", 1968, "이탈리아 로마"))
     mStudentList.add(Student("키엘리니", 1984, "이탈리아 아탈란타"))

//미뤄뒀던 mAdapter의 대입 진행
     mAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList)

//완성된 어댑터 변수를 리스트뷰와 연결
        studentListView.adapter = mAdapter

//학생 클릭 이벤트뷰 클릭 구현
        studentListView.setOnItemClickListener { parent, view, position, id ->
       //position - 몇 번째 줄을 눌렀는지 보기

//클릭 된 학생의 이름 토스트로 구현
            val clickedStudent = mStudentList[position]

            Toast.makeText(this, clickedStudent.name, Toast.LENGTH_SHORT).show()


        }

        studentListView.setOnItemLongClickListener { parent, view, position, id ->

//            우선 경고 확인창만 눌러보자

            val alert = AlertDialog.Builder(this)
            
            alert.setTitle("학생 삭제")
            alert.setMessage("정말 학생을 삭제하시겠습니까")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
//                확인 버튼이 눌렀을 때 실행 해줄 일
//                학생 삭제는 이떄 진행

                mStudentList.removeAt(position)

//            어댑터가 이를 확이하도록 한다

                mAdapter.notifyDataSetChanged()



            })
            alert.setNegativeButton("취소", null)

            alert.show()

            return@setOnItemLongClickListener true
        }



    }
}