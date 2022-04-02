import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import * as Yup from "yup";
import axios from "axios";

const SignupPage = () => {
  useEffect(() => {
    // http method | "restful API"
    // ------------|----------------
    // GET         | /users/:id
    // POST        | /users
    // PATCH, PUT  | /users/:id
    // DELETE      | /users/:id
    // axios
    //   .post({
    //     method: "POST",
    //     url: "http://127.0.0.1:8080/users/signup",
    //     data: {
    //       email: "111@naver.com",
    //       passwd: "111111",
    //     },
    //   })
    //   .then((res) => {
    //     console.log(res);
    //   });
    // axios
    //   .post("/users/signup", {
    //     email: "111@naver.com",
    //     passwd: "111111",
    //   })
    //   .then((res) => {
    //     console.log(res);
    //   });
  }, []);

  const [passwordType, setPasswordType] = useState("password");

  const loginSchema = Yup.object().shape({
    email: Yup.string().required("필수 입력사항입니다."),
    name: Yup.string().required("필수 입력사항입니다."),
    nickname: Yup.string().required("필수 입력사항입니다."),
    passwd: Yup.string().required("필수 입력사항입니다."),
  });

  return (
    <main className="h-screen flex justify-center items-center flex-col space-y-3">
      <article className="border border-gray-300  ">
        <div className="w-96 m-auto bg-white">
          <h1 className="text-center my-3">Instagram</h1>
          <h1 className="text-center text-gray-400 font-semibold">
            친구들의 사진과 동영상을 보려면
          </h1>
          <h1 className="text-center text-gray-400 font-semibold">
            가입하세요.
          </h1>

          <Formik
            initialValues={{ email: "", name: "", nickname: "", passwd: "" }}
            validationSchema={loginSchema}
            onSubmit={(values, { setSubmitting }) => {
              debugger;
              console.log(values);
              setSubmitting(false);
              // setTimeout(() => {
              //   alert(JSON.stringify(values, null, 2));
              // }, 400);
            }}
            enableReinitialize
            validateOnMount
          >
            {({ values, isSubmitting, isValid, handleChange, handleBlur }) => (
              <form>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 text-sm bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="text"
                    name="email"
                    placeholder="휴대폰 번호 또는 이메일주소"
                  />
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="text"
                    name="name"
                    placeholder="성명"
                  />
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="text"
                    name="nickname"
                    placeholder="사용자 이름"
                  />
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type={passwordType}
                    name="passwd"
                    placeholder="비밀번호"
                  />
                </div>
                <div className="mx-5 mb-2 mt-5">
                  <button
                    type="submit"
                    disabled={!isValid || isSubmitting}
                    className="bg-sky-300 text-white w-full py-1"
                  >
                    회원가입
                  </button>
                </div>
              </form>
            )}
          </Formik>
        </div>
      </article>
    </main>
  );
};

export default SignupPage;
