import React from "react";
import "./Modal.css";

const Modal = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header } = props;

  const popupText = ["신고", "팔로우 취소", "게시물로 이동", "퍼가기"];
  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          {popupText && popupText.map((title) => <main>{title}</main>)}
          <main>
            {" "}
            <button className="close" onClick={close}>
              취소
            </button>
          </main>
        </section>
      ) : null}
    </div>
  );
};
export default Modal;
