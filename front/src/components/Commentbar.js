import React from "react";
import { BiSmile } from "react-icons/bi";

const CommentBar = () => {
  return (
    <main className="h-max w-auto" > 
      <article className="flex  divide-x w-auto h-auto m-auto">
        <BiSmile className="m-3 col-span-1"></BiSmile>
        <div className=" my-auto w-72">
          <input
            classname="border border-gray-300 my-auto"
            type="text"
            placeholder="댓글 달기.."
            name="search"
          />
        </div>
        <div className="h-max content-end justify-items-center m-2 pl-3">
          <button className="text-sky-300 text-sm align-middle " type="text">
            개시
          </button>
        </div>
      </article>
    </main>
  );
};
export default CommentBar;