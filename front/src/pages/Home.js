import React from "react";
import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

function Home() {
  return (
    <main className="h-max justify-start px-10 items-start space-y-10">
      <hl className=" ">12</hl>

      <article className="border border-gray-300  ">
        <div className="w-96 m-5 bg-white">
          <div className="pl-3 pb-5 pt-5">
            <a href="#">
              {" "}
              계정{" "}
              <a className="pl-3" href="#">
                아이디
              </a>
            </a>
          </div>
        </div>
        <div>
          <Swiper
            className="shadow box-border h-50 w-50 border border-gray-300 m-5 mb-5 text-center grid grid-cols-3 divide-y"
            spaceBetween={10}
            slidesPerView={1}
            navigation
            pagination={{ clickable: true }}
          >
            <SwiperSlide>Slide 1</SwiperSlide>
            <SwiperSlide>Slide 2</SwiperSlide>
            <SwiperSlide>Slide 3</SwiperSlide>
            <SwiperSlide>Slide 4</SwiperSlide>
          </Swiper>
        </div>
      </article>
      <div>
        <Swiper
          className=" m-5 mb-0 text-center"
          spaceBetween={10}
          slidesPerView={3}
          navigation
          pagination={{ clickable: true }}
        >
          <SwiperSlide>Slide 1</SwiperSlide>
          <SwiperSlide>Slide 2</SwiperSlide>
          <SwiperSlide>Slide 3</SwiperSlide>
          <SwiperSlide>Slide 4</SwiperSlide>
          <SwiperSlide>Slide 5</SwiperSlide>
          <SwiperSlide>Slide 6</SwiperSlide>
        </Swiper>
      </div>
    </main>
  );
}
export default Home;
