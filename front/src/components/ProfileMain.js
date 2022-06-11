import { Form } from "formik";
import React, { useState } from "react";
import { AiOutlineUserAdd, AiOutlineDown } from "react-icons/ai";
import { BsThreeDots } from "react-icons/bs";

const ProfileMain = () => {
  return (
    <main className="h-max justify-start px10 items-start space-y-10 grid grid-cols-5">
      <article
        className=" h-max m-auto mt-4 w-96 col-start-2"
        style={{ maxWidth: "100vh", minWidth: "100vh" }}
      >
        <div className="bg-white flex-wrap col-start-2 ">
          <div className="grid grid-cols-10 pl-3 pb-3 pt-2">
            <img
              className="p-2 col-span-3 h-10 w-96 rounded-full"
              src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fi.pinimg.com%2F736x%2Fdf%2Fcb%2Fd0%2Fdfcbd0982731d27df1ee3d6d3f584771.jpg&type=sc960_832"
              alt=""
              style={{
                maxHeight: "20vh",
                minHeight: "20vh",
                maxWidth: "20vh",
                minWidth: "20vh",
              }}
            ></img>
            <div className="col-span-1">
              <div className=" p-1 text-xl">계정</div>
              <div className="pt-6">게시물 11</div>
              <div className="pt-10">dcdgkhdvcvchxjvhcxjbhcvhbkjvchbcv</div>
            </div>

            <div className="col-span-1">
              <button className=" border w-30 h-6 m-1 px-1 text-xs">
                메시지 보내기
              </button>

              <div className="pt-7">팔로워 3232</div>
            </div>

            <div className="col-span-1 ">
              <div className="flex flex-row">
                <button className="border w-16 h-6 m-1 pl-5">
                  <AiOutlineUserAdd />
                </button>
                <button className="border w-8 h-6 m-1 pl-1">
                  <AiOutlineDown />
                </button>
              </div>
              <div className="flex-col pt-7 pl-5"> 팔로우 10</div>
            </div>

            <button className="h-3 pl-6 scale-125 mt-2">
              <BsThreeDots />
            </button>
          </div>
        </div>
      </article>
    </main>
  );
};
export default ProfileMain;
