package kr.ac.kpu.ce2019152012.hair_you.designer.fragment

import android.os.Bundle

import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.ac.kpu.ce2019152012.hair_you.databinding.FragmentDesignerHomeBinding
import kr.ac.kpu.ce2019152012.hair_you.dto.UserDto

import retrofit2.Retrofit

class DesignerHomeFragment : Fragment() {

    private lateinit var binding: FragmentDesignerHomeBinding
    private lateinit var myReservationAdapter: MyRecyclerAdapter
    private lateinit var myReservationRecyclerView: RecyclerView
    private lateinit var tmpItems : ArrayList<tmpItem>

    lateinit var retrofit: Retrofit

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //binding = FragmentDesignerHomeBinding.bind(view)


        /*
        //spring test
        retrofit=Retrofit.Builder()
            .baseUrl("https://59.16.253.112:8080/")    // 우리 집에서 서버 열 때 url 아마도..?
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitApi = retrofit.create(RetrofitAPI ::class.java )
        var call = retrofitApi.getPosts()
        */


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //myReservationAdapter=view.findViewById(R.layout.recyclerview_current_reservation_item)

        binding = FragmentDesignerHomeBinding.inflate(inflater, container, false)

        myReservationRecyclerView=binding.curReserRcv
        myReservationAdapter=
            MyRecyclerAdapter()


        myReservationRecyclerView.adapter=myReservationAdapter
        myReservationRecyclerView.layoutManager= LinearLayoutManager( getActivity())
        myReservationRecyclerView.layoutManager = LinearLayoutManager( getActivity(), RecyclerView.HORIZONTAL,false )
                                                                    // 프래그먼트는 this 대신  getActivity().getApplicationContext() 사용

        binding.refreshFab.setOnClickListener {
            Log.d("event", "refreshFab clicked")
        }

        return binding.root
    }


}