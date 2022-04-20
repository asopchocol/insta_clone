import React, { Component, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper";
import "swiper/css";
import "swiper/css/pagination";
import "../Modal2.css";
import CommentBar from "./Commentbar";

const CommentPop = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header } = props;

  const imageDatas = [
    "https://cdn.pixabay.com/photo/2018/06/13/18/20/waves-3473335__480.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMJ2fSRUwbu1gpU8eS3wiyKsBCtphO8HpFow&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRljMbXgwicwW2cKPlcUTfWXewaPDAGb9ubw&usqp=CAU",
  ];

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section className="flex">
          <main>
            {" "}
            <button className="close col-span-1" onClick={close}>
              취소
            </button>
          </main>
          <div>
            <Swiper
              className="shadow box-border border border-gray-300 col-span-1 m-5 mb-3 bg-black"
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
          <div>
            <CommentBar classname="col-span-1"></CommentBar>
          </div>
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
