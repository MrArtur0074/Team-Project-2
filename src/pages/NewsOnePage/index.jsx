import React, { useEffect, useState } from "react";
import MainLayout from "../../components/Layout/MainLayout";
import NewsCard from "../../components/NewsCard";
import TelegramSvg from "./assets/telegram.svg";
import VkSvg from "./assets/vk.svg";
import InstagramSvg from "./assets/instagram.svg";
import axios from "axios";
import { useParams, Link } from "react-router-dom";
import { apiUrl } from "../../api";
import { Breadcrumbs } from "@mui/material";

const NewsOnePage = () => {
  const { id } = useParams();
  const [news, setNews] = useState(null);
  const [news1, setNews1] = useState(null);
  const [news2, setNews2] = useState(null);

  useEffect(() => {
    axios.get(`${apiUrl}/news/${id}`).then((resp) => setNews(resp.data));
    axios.get(`${apiUrl}/news/3`).then((resp) => setNews1(resp.data));
    axios.get(`${apiUrl}/news/1`).then((resp) => setNews2(resp.data));
  }, [id]);

  return (
    <MainLayout>
      <div className="container mx-auto p-4">
        <div className="my-4">
          <Breadcrumbs separator="›" aria-label="breadcrumb">
            <Link className="hover:underline underline-offset-1 text-ourblue font-medium text-2xl" to="/">
              Главная
            </Link>
            <Link className="hover:underline underline-offset-1 text-ourblue font-medium text-2xl" to="/news">
              Новости
            </Link>
            <p className="text-[rgba(42, 85, 115, 0.5)] font-medium text-2xl">
              Читать новость
            </p>
          </Breadcrumbs>
        </div>
        <h2 className="text-ourblue text-[34px] font-bold mt-10">Читать новость</h2>
        <div className="grid grid-cols-12 gap-5">
          <div className="col-span-12 sm:col-span-7">
            <p className="text-[#9d9d9d] mt-12">
              {news ? news.createdDate.split("T")[0] : "Загрузка..."}
            </p>
            <h1 className="text-[#AB3D51] text-2xl sm:text-4xl font-bold mt-8">
              {news ? news.title : "Загрузка..."}
            </h1>
            <p className="mt-5 text-[#2A5573] leading-7 text-base">
              {news ? news.text : "Загрузка..."}
            </p>
            <div className="mt-[64px]">
              <p className="text-[#2A5573]">
                Источник новости и фото: Национальный фонд развития здравоохранения КР
              </p>
              <div className="flex gap-16 mt-3">
                <p className="text-[#2A5573]">Поделиться:</p>
                <div className="flex items-center gap-4">
                  <a href="#">
                    <img className="w-[32px]" src={TelegramSvg} alt="Telegram" />
                  </a>
                  <a href="#">
                    <img className="w-[32px]" src={VkSvg} alt="VK" />
                  </a>
                  <a href="#">
                    <img className="w-[32px]" src={InstagramSvg} alt="Instagram" />
                  </a>
                </div>
              </div>
            </div>
          </div>
          <div className="flex flex-col sm:items-end col-span-12 sm:col-span-5 gap-[50px]">
            <div className="sm:max-w-[400px] grid gap-[50px]">
              {news1 && (
                <NewsCard
                  id={news1.id}
                  title={news1.title}
                  description={news1.description}
                  createdDate={news1.createdDate}
                  img={news1.imageUrl}
                />
              )}
              {news2 && (
                <NewsCard
                  id={news2.id}
                  title={news2.title}
                  description={news2.description}
                  createdDate={news2.createdDate}
                  img={news2.imageUrl}
                />
              )}
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};

export default NewsOnePage;
