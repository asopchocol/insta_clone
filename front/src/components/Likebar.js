import React, { useState } from "react";
import { FaRegComment, FaRegHeart } from "react-icons/fa";
import { FiSend } from "react-icons/fi";
import { RiBookmarkLine } from "react-icons/ri";

const LikeBar = () => {
  const [likeCount, setLikeCount] = useState(0);
  const [userLike, setUserLike] = useState(false);

  const onIncrease = () => {
    setLikeCount((prevCount) => prevCount + 1);
  };
  const onDecrease = () => {
    setLikeCount((prevCount) => prevCount - 1);
  };

  return (
    <main>
      <div className="grid grid-cols-12 gap-x-0 ml-3">
        <button>
          <FaRegHeart
            className=" col-span-1"
            onClick={() => {
              setUserLike((prev) => !prev);
              userLike ? onDecrease() : onIncrease();
            }}
          ></FaRegHeart>
        </button>

        <FaRegComment className="col-span-1"></FaRegComment>
        <FiSend className="col-span-1"></FiSend>
        <RiBookmarkLine className="col-start-12 col-span-1"></RiBookmarkLine>
      </div>
      <div className="p-3 text-xs font-semibold">좋아요 {likeCount}개</div>
    </main>
  );
};
export default LikeBar;
