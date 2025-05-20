import React from "react";
import { Link } from "react-router-dom";

type Props = {
  id: number;
  title: string;
  description: string;
  image: string;
  date: string;
};

function NewsCard({ id, title, description, image, date }: Props) {
  return (
    <>
      <div>
        <Link to={`/news/${id}`}>
          <div className="bg-[#EBEDEE] rounded-lg overflow-hidden w-[100%]">
            <div className="">
              <img src={image} alt="NewsImg" className=" w-[100%] h-[200px] "/>
            </div>
            <div className="p-4 grid gap-2">
              <p className="text-[#2A5573]/50">
                {date ? date.split("T")[0] : "Загрузка..."}
              </p>
              <h3 className="text-[#2A5573] font-bold text-lg">{title}</h3>
              <p className="text-[#2A5573]/50 ">{description}</p>
            </div>
          </div>
        </Link>
      </div>
    </>
  );
}

export default NewsCard;
