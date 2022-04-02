import { Formik } from "formik";
import React, { useState } from "react";
import * as Yup from "yup";

const SignupPage = () => {
  const [passwordType, setPasswordType] = useState("password");
  const loginSchema = Yup.object().shape({
    email: Yup.string().required("필수 입력사항입니다."),
    name: Yup.string().required("필수 입력사항입니다."),
    id: Yup.string().required("필수 입력사항입니다."),
    password: Yup.string().required("필수 입력사항입니다."),
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
          <div className="mx-5 mb-2 mt-5">
            <button className="bg-blue-500 text-white w-full py-1">
              Facebook으로 로그인
            </button>
          </div>
          <div className="text-center font-normal text-sm  mb-3 mt-3">또는</div>

          <Formik
            initialValues={{ email: "", password: "" }}
            validationSchema={loginSchema}
            onSubmit={(values, { setSubmitting }) => {
              setTimeout(() => {
                alert(JSON.stringify(values, null, 2));
                setSubmitting(false);
              }, 400);
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
                    type="email"
                    placeholder="휴대폰 번호 또는 이메일주소"
                  />
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="name"
                    placeholder="성명"
                  />
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type="id"
                    placeholder="사용자 이름"
                  />
                </div>
                <div className="mx-5 mb-2">
                  <input
                    className="border border-gray-300 bg-gray-50 w-full p-1"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type={passwordType}
                    placeholder="비밀번호"
                  />
                </div>
                <div className="mx-5 mb-2 mt-5">
                  <button
                    disabled={!isValid || isSubmitting}
                    className="bg-sky-300 text-white w-full py-1"
                  >
                    로그인
                  </button>
                </div>

                <div className="text-center font-medium mb-2 mt-5 ">
                  <a href="#">Facebook으로 로그인</a>
                </div>
                <div className="text-center text-xs font- thin mb-6 mt-5">
                  <a href="#">비밀번호를 잊으셨나요?</a>
                </div>
              </form>
            )}
          </Formik>
        </div>
      </article>

      <article className="border border-gray-300">
        <div className="w-96 m-auto bg-white">
          <div className="text-center font-medium mb-5 mt-5 ">
            계정이 없으신가요?
            <a className="text-blue-500" href="/signup">
              가입하기
            </a>
          </div>
        </div>
      </article>
    </main>
  );
};

export default SignupPage;
