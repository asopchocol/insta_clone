import React, { Component, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import { BsThreeDots } from "react-icons/bs";
import { Pagination } from "swiper";
import "swiper/css";
import "swiper/css/pagination";
import Navbar from "../components/Navbar";
import Modal from "../Modal";
import CommentBar from "../components/Commentbar";
import LikeBar from "../components/Likebar";
import CommentPop from "../components/Commentpop";

const Home = () => {
  const imageDatas = [
    "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fi.pinimg.com%2F736x%2Fdf%2Fcb%2Fd0%2Fdfcbd0982731d27df1ee3d6d3f584771.jpg&type=sc960_832",
    "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAzMTNfNSAg%2FMDAxNjQ3MTQxNjU4NTc3.ro0DLzCnyS8K598JU0Jh4cyuvm9nfquvxbCxfq225_gg.qYD8o8IOMBds5fLyl9tTP1I10O0-1pD916XN2yR2Dpsg.JPEG.gnssudpdy%2F1647141380147.jpg&type=sc960_832",
    "https://search.pstatic.net/common/?src=http%3A%2F%2Fcafefiles.naver.net%2FMjAxOTA4MTVfMjEg%2FMDAxNTY1ODc0MTQ3NDc5.MiyJgZRDbtecLkGw3Dh85NkkARuuX2OfrkyEJqmeteUg.-6ohkYxh7RDFq8s1dpBxCOhDBAJLafro8v8MJRyiWN0g.JPEG%2FexternalFile.jpg&type=sc960_832",
    "https://cdn.pixabay.com/photo/2018/06/13/18/20/waves-3473335__480.jpg",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMJ2fSRUwbu1gpU8eS3wiyKsBCtphO8HpFow&usqp=CAU",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRljMbXgwicwW2cKPlcUTfWXewaPDAGb9ubw&usqp=CAU",
  ];
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };
  const [modalOpen2, setModalOpen2] = useState(false);

  const openModal2 = () => {
    setModalOpen2(true);
  };
  const closeModal2 = () => {
    setModalOpen2(false);
  };
  return (
    <>
      <Navbar />
      <main className="h-max justify-start px-10 items-start space-y-10">
        <hl className=" "></hl>

        <article className="border border-gray-300 w-96 m-auto">
          <div className="bg-white flex-wrap">
            <div className="grid grid-cols-6 pl-3 pb-5 pt-5">
              <a className="col-span-1" href="#">
                계정
              </a>
              <a className="col-span-1" href="#">
                아이디
              </a>
              <button
                onClick={openModal}
                className="col-start-6 col-span-1 pl-5"
              >
                <BsThreeDots />
              </button>
            </div>

            <React.Fragment>
              <Modal
                open={modalOpen}
                close={closeModal}
                header="Modal headiong"
              ></Modal>
            </React.Fragment>
          </div>

          <div>
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
          </div>
          <LikeBar></LikeBar>
          <button
            onClick={openModal2}
            className="col-start-6 col-span-1 pl-3 text-xs text-gray-400"
          >
            더보기
          </button>
          <div>
            <React.Fragment>
              <CommentPop
                open={modalOpen2}
                close={closeModal2}
                header="Modal headiong"
              ></CommentPop>
            </React.Fragment>
          </div>
          <div className="border-t border-gray-400 w-auto">
            <CommentBar className=""></CommentBar>
          </div>
        </article>
      </main>
    </>
  );
};
export default Home;
/* <div>
            <React.Fragment>
              <CommentPop
                open={modalOpen}
                close={closeModal}
                header="Modal headiong"
              ></CommentPop>
            </React.Fragment>
          </div> */
