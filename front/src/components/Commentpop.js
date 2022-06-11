import React, { Component, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper";
import "swiper/css";
import "swiper/css/pagination";
import "../Modal2.css";
import CommentBar from "./Commentbar";
import LikeBar from "./Likebar";

const CommentPop = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header ,image} = props;

  const imageDatas = [
    "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fi.pinimg.com%2F736x%2Fdf%2Fcb%2Fd0%2Fdfcbd0982731d27df1ee3d6d3f584771.jpg&type=sc960_832",
    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAzMTNfNSAg%2FMDAxNjQ3MTQxNjU4NTc3.ro0DLzCnyS8K598JU0Jh4cyuvm9nfquvxbCxfq225_gg.qYD8o8IOMBds5fLyl9tTP1I10O0-1pD916XN2yR2Dpsg.JPEG.gnssudpdy%2F1647141380147.jpg&type=sc960_832",
    "https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxOTA4MTVfMjEg%2FMDAxNTY1ODc0MTQ3NDc5.MiyJgZRDbtecLkGw3Dh85NkkARuuX2OfrkyEJqmeteUg.-6ohkYxh7RDFq8s1dpBxCOhDBAJLafro8v8MJRyiWN0g.JPEG%2FexternalFile.jpg&type=sc960_832",
    "https://cdn.pixabay.com/photo/2018/06/13/18/20/waves-3473335__480.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMJ2fSRUwbu1gpU8eS3wiyKsBCtphO8HpFow&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRljMbXgwicwW2cKPlcUTfWXewaPDAGb9ubw&usqp=CAU",
  ];

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div className={`modal ${open ? "commentPopModal" : ''}`}>
      {open ? (
        <section>
          <header>
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>

          <div classname="">
            <Swiper
              className="flex-row box-border border border-gray-300 m-5 mb-3 bg-black"
              spaceBetween={10}
              slidesPerView={1}
              pagination={true}
              modules={[Pagination]}
            >
              {imageDatas &&
                imageDatas.map((url) => (
                  <SwiperSlide className="w-100 h-full">
                    <img
                      className="m-auto w-100 h-full object-contain"
                      src={url}
                      alt=""
                      style={{ maxHeight: "70vh", minHeight: "50vh" }}
                    />
                  </SwiperSlide>
                ))}
            </Swiper>
          </div>
          <section className="">
            <div>
              <LikeBar></LikeBar>
            </div>

            <div className="flex-col border-t border-gray-400 w-auto">
              <CommentBar />
            </div>
          </section>
        </section>
      ) : null}
      
    </div>
  );
};
export default CommentPop;

{
  /* <div>
              <Swiper
                className="shadow box-border border border-gray-300 m-5 mb-3 bg-black"
                spaceBetween={10}
                slidesPerView={1}
                pagination={true}
                modules={[Pagination]}
              >
                {imageDatas &&
                  imageDatas.map((url) => (
                    <SwiperSlide className="w-100 h-full">
                      <img
                        className="m-auto w-100 h-full object-contain"
                        src={url}
                        alt=""
                        style={{ maxHeight: "70vh", minHeight: "50vh" }}
                      />
                    </SwiperSlide>
                  ))}
              </Swiper>
            </div> */
}
