import React from "react";
import { BiSmile } from "react-icons/bi";

const CommentBar = () => {
  return (
    <main className="h-max justify-start items-start space-y-10">
      <article className="border border-gray-300 grid grid-cols-10 divide-x w-96 m-auto">
        <BiSmile className="m-2 col-span-1"></BiSmile>
        <div className="col-span-3 my-auto w-96">
          <input
            classname="border border-gray-300 box-border mr-20 my-auto w-96"
            type="text"
            placeholder="댓글 달기.."
            name="search"
          />
        </div>
        <div className="col-span-1 col-start-10 my-auto w-96 ">
          <button className="text-sky-300 text-sm pl-1" type="text">
            개시
          </button>
        </div>
      </article>
    </main>
  );
};
export default CommentBar;
