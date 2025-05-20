import React, {useEffect, useState, useRef, useLayoutEffect} from "react";
import MainLayout from "../../components/Layout/MainLayout";
import DonorImg from "./assets/donor1.jpg";
import NewsCard from "../../components/NewsCard";
import TelegramSvg from "./assets/telegram.svg";
import VkSvg from "./assets/vk.svg";
import InstagramSvg from "./assets/instagram.svg";
import axios from "axios";
import {useParams} from "react-router-dom";
import {apiUrl} from "../../api";
import {Breadcrumbs} from "@mui/material";
import {Link} from "react-router-dom";

type Props = {};

export type News = {
  id: number;
  title: string;
  description: string;
  image: string;
  date: string;
};

function NewsOnePage({}: Props) {
  const { id } = useParams<{ id: string }>();
  const [news, setNews] = useState<News | null>(null);
  const apiUrl = "http://localhost:8080";

  useEffect(() => {
    getNews().then((data) => {
      setNews(data);
    });
  }, []);

  async function getNews() {
    try {
      const newsRequest = await axios.get(`${apiUrl}/news-api/news/${id}`, {});
      console.log(newsRequest.data);
      return newsRequest.data;
    } catch (err) {
      console.log(err);
    }
  }

  return (
          <MainLayout>
            <div className=" container mx-auto p-4">
              <div className="my-4">
                <Breadcrumbs separator="›" aria-label="breadcrumb">
                  <Link
                          className="hover:underline underline-offset-1"
                          color="inherit"
                          to="/">
                    <p className="text-ourblue font-medium text-2xl">Главная</p>
                  </Link>
                  <Link
                          className="hover:underline underline-offset-1"
                          color="inherit"
                          to="/news">
                    <p className="text-ourblue font-medium text-2xl">Новости</p>
                  </Link>
                  <Link
                          className="hover:underline underline-offset-1"
                          color="inherit"
                          to="">
                    <p className="text-[rgba(42, 85, 115, 0.5)] font-medium text-2xl">
                      Читать новость
                    </p>
                  </Link>
                </Breadcrumbs>
              </div>
              <h2 className="text-ourblue text-[34px] font-bold mt-10">
                Читать новость
              </h2>
              <div className="">
                <img src={news?.image} alt="NewsImg" className=" w-[100%] h-[300px] "/>
              </div>
              <div className="grid grid-cols-12 gap-5">
                <div className="col-span-12 sm:col-span-7">
                  <p className="text-[#9d9d9d] mt-12">
                    {news ? news.date.split("T")[0] : "Загрузка..."}
                  </p>
                  <h1 className="text-[#AB3D51] text-2xl sm:text-4xl font-bold mt-8">
                    {news ? news.title : "Загрузка..."}
                  </h1>
                  <p className=" mt-5 text-[#2A5573] leading-7 text-base">
                    {news ? news.description : "Загрузка..."}
                  </p>
                  <div className="mt-[64px]">
                    <p className="text-[#2A5573]">
                      Источник новости и фото: Национальный фонд развития
                      здравоохранения КР
                    </p>
                    <div className="flex gap-16 mt-3">
                      <p className="text-[#2A5573]">Поделиться:</p>
                      <div className="flex items-center gap-4 ">
                        <a href="#">
                          <img className=" w-[32px]" src={TelegramSvg} alt="icon"/>
                        </a>
                        <a href="#">
                          <img className=" w-[32px]" src={VkSvg} alt="icon"/>
                        </a>
                        <a href="#">
                          <img className=" w-[32px]" src={InstagramSvg} alt="icon"/>
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="flex flex-col sm:items-end col-span-12 sm:col-span-5 gap-[50px]">
                  {/* {news && [news1, news2, news3].map((item) =>
              <div className='sm:max-w-[400px]'>
                <NewsCard id={item.id} title={item.title} description={item.description} dateOfCreate={item.dateOfCreate} img={item.img} />
              </div>
            )} */}
                </div>
              </div>
            </div>
          </MainLayout>
  );
}


export default NewsOnePage;
