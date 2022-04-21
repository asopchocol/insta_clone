import React from "react";
import { BiSmile } from "react-icons/bi";

const CommentBar = () => {
  return (
    <main className="h-max w-80">
      <article className="flex divide-x w-auto h-auto m-auto">
        <BiSmile className="m-2 col-span-1"></BiSmile>
        <div className=" my-auto w-96">
          <input
            classname="border border-gray-300 mr-20 my-auto"
            type="text"
            placeholder="댓글 달기.."
            name="search"
          />
        </div>
        <div className="text-right h-max">
          <button className="text-sky-300 text-sm pr-6" type="text">
            개시
          </button>
        </div>
      </article>
    </main>
  );
};
export default CommentBar;
