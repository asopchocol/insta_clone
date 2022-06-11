import React, { Component, useState } from "react";
import { AiOutlineTable } from "react-icons/ai";
import { BiMoviePlay } from "react-icons/bi";
import { RiAccountPinBoxLine } from "react-icons/ri";

const ProfileContent = () => {
  return (
    <main className="h-max ">
      <article className="grid grid-cols-11">
        <div className="col-start-2 col-span-9 border-t w-full">
          <div className="grid grid-cols-3">
            <div className="col-span-1"></div>
            <div className="grid grid-cols-3">
              <div className="p-3 flex flex-row">
                <div className="p-1">
                  <AiOutlineTable />
                </div>
                게시물
              </div>
              <div className="p-3 flex flex-row">
                <div className="p-1">
                  <BiMoviePlay />
                </div>
                릴스
              </div>
              <div className="p-3 flex flex-row">
                <div className="p-1">
                  <RiAccountPinBoxLine />
                </div>
                태그됨
              </div>
            </div>
            <div className="col-span-1"></div>
          </div>
        </div>
      </article>
    </main>
  );
};

export default ProfileContent;
