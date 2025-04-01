import React, { useEffect, useState } from "react";
import donor1 from "./assets/donor1.jpg";
import donor2 from "./assets/donor2.jpg";
import donor3 from "./assets/donor3.jpg";
import donor4 from "./assets/donor4.jpg";
import donor5 from "./assets/donor5.jpg";
import donor6 from "./assets/donor6.jpg";
import MainLayout from "../../components/Layout/MainLayout";
import NewsCard from "../../components/NewsCard";
import Pagination from "@mui/material/Pagination";
import axios from "axios";
import { Breadcrumbs } from "@mui/material";
import { Link } from "react-router-dom";
import { apiUrl } from "../../api";

const newsArray = [
  { id: 1, title: "Количество доноров в Кыргызстане уменьшилось на 36,7% после Covid-19", description: "Не следует, однако, забывать...", img: donor1, dateOfCreate: "18/01/22" },
  { id: 2, title: "Цена жизни. Как стать донором крови и какую пользу это принесет", description: "Не следует, однако, забывать...", img: donor2, dateOfCreate: "18/01/22" },
  { id: 3, title: "Помощь Баткену. За два дня кровь сдали 643 человека", description: "Не следует, однако, забывать...", img: donor3, dateOfCreate: "18/01/22" },
  { id: 4, title: "Сотрудники «Газпром Кыргызстан» поддержали акцию по сбору донорской крови", description: "Не следует, однако, забывать...", img: donor4, dateOfCreate: "18/01/22" },
  { id: 5, title: "Центр крови просит у кыргызстанцев помощи. Запасы практически исчерпаны", description: "Не следует, однако, забывать...", img: donor5, dateOfCreate: "18/01/22" },
  { id: 6, title: "Информация для граждан, желающих сдать кровь для пострадавших на границе", description: "Не следует, однако, забывать...", img: donor6, dateOfCreate: "18/01/22" },
];

function NewsPage() {
  const [newskLists, setNewslists] = useState(null);
  const [totalPages, setTotalPages] = useState(1);
  const [data, setData] = useState();
console.log(newskLists,data);

  useEffect(() => {
    axios.get(`${apiUrl}/news`).then((resp) => {
      setData(resp.data);
      setTotalPages(resp.data.totalPages);
      setNewslists(resp.data.content);
      console.log(resp.data);
    });
  }, []);

  return (
    <MainLayout>
      <div className="container mx-auto p-4">
        <div className="my-4">
          <Breadcrumbs separator="›" aria-label="breadcrumb">
            <Link className="hover:underline underline-offset-1" to="/">
              <p className="text-ourblue font-medium text-2xl">Главная</p>
            </Link>
            <Link className="hover:underline underline-offset-1" to="">
              <p className="text-[rgba(42, 85, 115, 0.5)] font-medium text-2xl">Новости</p>
            </Link>
          </Breadcrumbs>
        </div>
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-5">
          {newsArray.map((item) => (
            <NewsCard key={item.id} id={item.id} title={item.title} description={item.description} createdDate={item.dateOfCreate} img={item.img} />
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
