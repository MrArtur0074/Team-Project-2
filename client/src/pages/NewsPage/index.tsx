import React, { useEffect, useState } from "react";
import MainLayout from "../../components/Layout/MainLayout";
import NewsCard from "../../components/NewsCard";
import Pagination from "@mui/material/Pagination/Pagination";
import {Breadcrumbs} from "@mui/material";
import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "../../App/auth/authProvider";
import axios from "axios";


// export const newsArray = [
//   {
//     id: 1,
//     title:
//       "Количество доноров  в Кыргызстане уменьшилось на 36,7% после Covid-19",
//     description:
//       "Не следует, однако, забывать, что существующая теория требует от нас анализа кластеризации усилий! В целом, конечно, высокотехнологичная концепция общественного уклада позволяет оценить значение соответствующих условий активизации. Задача организации, в особенности же разбавленное изрядной долей эмпатии, рациональное мышление требует анализа форм воздействия.",
//     img: donor1,
//     dateOfCreate: "18/01/22",
//   },
//   {
//     id: 2,
//     title: "Цена жизни. Как стать донором крови и какую пользу это принесет",
//     description:
//       "Не следует, однако, забывать, что существующая теория требует от нас анализа кластеризации усилий! В целом, конечно, высокотехнологичная концепция общественного уклада позволяет оценить значение соответствующих условий активизации. Задача организации, в особенности же разбавленное изрядной долей эмпатии, рациональное мышление требует анализа форм воздействия.",
//     img: donor2,
//     dateOfCreate: "18/01/22",
//   },
//   {
//     id: 3,
//     title: "Помощь Баткену. За два дня кровь сдали 643 человека",
//     description:
//       "Не следует, однако, забывать, что существующая теория требует от нас анализа кластеризации усилий! В целом, конечно, высокотехнологичная концепция общественного уклада позволяет оценить значение соответствующих условий активизации. Задача организации, в особенности же разбавленное изрядной долей эмпатии, рациональное мышление требует анализа форм воздействия.",
//     img: donor3,
//     dateOfCreate: "18/01/22",
//   },
//   {
//     id: 4,
//     title:
//       "Сотрудники «Газпром Кыргызстан» поддержали акцию по сбору донорской крови",
//     description:
//       "Не следует, однако, забывать, что существующая теория требует от нас анализа кластеризации усилий! В целом, конечно, высокотехнологичная концепция общественного уклада позволяет оценить значение соответствующих условий активизации. Задача организации, в особенности же разбавленное изрядной долей эмпатии, рациональное мышление требует анализа форм воздействия.",
//     img: donor4,
//     dateOfCreate: "18/01/22",
//   },
//   {
//     id: 5,
//     title:
//       "Центр крови просит у кыргызстанцев помощи. Запасы практически исчерпаны",
//     description:
//       "Не следует, однако, забывать, что существующая теория требует от нас анализа кластеризации усилий! В целом, конечно, высокотехнологичная концепция общественного уклада позволяет оценить значение соответствующих условий активизации. Задача организации, в особенности же разбавленное изрядной долей эмпатии, рациональное мышление требует анализа форм воздействия.",
//     img: donor5,
//     dateOfCreate: "18/01/22",
//   },
//   {
//     id: 6,
//     title:
//       "Информация для граждан, желающих сдать кровь для пострадавших на границе",
//     description:
//       "Не следует, однако, забывать, что существующая теория требует от нас анализа кластеризации усилий! В целом, конечно, высокотехнологичная концепция общественного уклада позволяет оценить значение соответствующих условий активизации. Задача организации, в особенности же разбавленное изрядной долей эмпатии, рациональное мышление требует анализа форм воздействия.",
//     img: donor6,
//     dateOfCreate: "18/01/22",
//   },
// ];


type Props = {};

export type News = {
  id: number;
  title: string;
  description: string;
  image: string;
  date: string;
};

function NewsPage({ }: Props) {
  const auth = useAuth();

  const [totalPages, setTotalPages] = useState<number | 1>();
  const nav = useNavigate();
  const [news, setNews] = useState<News[]>([]);
  const apiUrl = "http://localhost:8080";

  useEffect(() => {
    getNews().then((data) => {
      setNews(data);
    });
  }, []);

  async function getNews() {
    try {
      const newsRequest = await axios.get(`${apiUrl}/news-api/news`, {});
      console.log(newsRequest.data);
      return newsRequest.data;
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <MainLayout>
      <div className="container mx-auto p-4">
        {auth?.isAuthenticated ? <div onClick={()=> {nav("/news/create")}}>Create New</div> : null}
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
              to="">
              <p className="stext-[rgba(42, 85, 115, 0.5)] font-medium text-2xl">
                Новости
              </p>
            </Link>
          </Breadcrumbs>
        </div>
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-5">
          {news &&
            news.map((item) => (
              <NewsCard
                      key={item.id}
                id={item.id}
                title={item.title}
                image={item.image}
                date={item.date}
                description={item.description}
              />
            ))}
        </div>
        <div className="flex justify-center my-[30px]">
          <Pagination count={totalPages} color="secondary" />
        </div>
      </div>
    </MainLayout>
  );
}

export default NewsPage;
