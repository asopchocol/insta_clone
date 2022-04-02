import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";

import { Pagination } from "swiper";
import "swiper/css";
import "swiper/css/pagination";
import Navbar from "../components/Navbar";

const Home = () => {
  const imageDatas = [
    "https://cdn.pixabay.com/photo/2018/06/13/18/20/waves-3473335__480.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMJ2fSRUwbu1gpU8eS3wiyKsBCtphO8HpFow&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRljMbXgwicwW2cKPlcUTfWXewaPDAGb9ubw&usqp=CAU",
  ];

  return (
    <>
      <Navbar />
      <main className="h-max justify-start px-10 items-start space-y-10">
        <hl className=" ">12</hl>

        <article className="border border-gray-300 w-96 m-auto">
          <div className="bg-white">
            <div className="pl-3 pb-5 pt-5">
              <a href="#">
                계정
                <a className="pl-3" href="#">
                  아이디
                </a>
              </a>
            </div>
          </div>
          <div>
            <Swiper
              className="shadow box-border border border-gray-300 m-5 bg-black"
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
        </article>
      </main>
    </>
  );
};
export default Home;
