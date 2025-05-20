import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useAuth} from "../../App/auth/authProvider";
import "./CreateNews.scss";
import MainLayout from "../../components/Layout/MainLayout";
import {Container} from "@mui/material";

interface User {
      username?: string,
      email?: string,
      profile: UserProfile
}
interface UserProfile {
      name: string,
      surname: string,
      dateOfBirth: string,
      bloodType: string,
      gender: string
}


const CreateNews = () => {
      const auth = useAuth();
      const [user, setUser] = useState<User | null>(null);
      const navigate = useNavigate();
      const apiUrl = "http://localhost:8080"
      const emptyUserProfile: UserProfile = {
            name: "",
            surname: "",
            dateOfBirth: "",
            bloodType: "",
            gender: ""
      };
      const [title, setTitle] = useState("");
      const [description, setDescription] = useState("");
      const [image, setImage] = useState("");
      const [date, setDate] = useState("");


      async function getProfile(token: string): Promise<User> {
            try {
                  const userRequest = await axios.get(`${apiUrl}/user-api/profile`, {
                        headers: {
                              "Authorization": `Bearer ${token}`,
                        },
                  });
                  return userRequest.data;
            }
            catch (error) {
                  if (axios.isAxiosError(error) && error.response) {
                        const status = error.response.status;

                        if(status == 401
                                && auth?.refreshToken && auth?.refreshAccessToken) {
                              const accessToken = await auth?.refreshAccessToken(auth?.refreshToken);
                              if(accessToken) {
                                    try {
                                          const userRequest = await axios.get(`http://127.0.0.1:8080/user-api/profile`, {
                                                headers: {
                                                      "Authorization": `Bearer ${accessToken}`,
                                                },
                                          });
                                          return userRequest.data;
                                    } catch {
                                          console.error("An unexpected error occurred");
                                          return { email: "", username: "", profile: emptyUserProfile};
                                    }
                              }
                              else {
                                    navigate("/login/");
                                    return { email: "", username: "", profile: emptyUserProfile};
                              }
                        }
                        else {
                              navigate("/login/");
                              console.error("An unexpected error occurred");
                              return { email: "", username: "", profile: emptyUserProfile};
                        }
                  }
                  else {
                        navigate("/login/");
                        console.error("An unexpected error occurred");
                        return { email: "", username: "", profile: emptyUserProfile};
                  }
            }
      }

      useEffect(() => {
            if(auth?.accessToken){
                  getProfile(auth.accessToken).then((data) => {
                        setUser(data);
                        if(data?.username !== "admin123") {
                              console.log(`${user?.username} not admin`);
                              navigate("/");
                        }
                  });
            } else {
                  navigate("/login/");
            }
      }, []);


      async function createNews() {
            if (!title || !description || !image || !date) {
                  alert("You have empty inputs!");
                  return null;
            }
            const formData = {
                  title: title,
                  description: description,
                  image: image,
                  date: date,
            };
            console.log(formData);
            await createVacancyRequest(formData);
      }

      async function createVacancyRequest(formData:any) {
            const accessToken = localStorage.getItem("donationAccessToken");
            try {
                  const response = await fetch(`${apiUrl}/news-api/news/create`, {
                        method: "POST",
                        headers: {
                              Authorization: `Bearer ${accessToken}`,
                              "Content-Type": "application/json",
                        },
                        body: JSON.stringify(formData),
                  });

                  if (response.ok) {
                        alert("Новость успешно создана");
                        navigate("/news");
                  } else {
                        alert("Ошибка при создании новости");
                        const errorData = await response.json();
                        console.log(errorData);
                        console.log(JSON.stringify(formData));
                  }
            } catch (err) {
                  console.error("Error:", err);
            }
      }

      return (
              <>
                    <MainLayout>
                          <Container>
                                <div className="newNewssub">
                                      <div>
                                            <h2 className="h1">Создание новости</h2>
                                      </div>
                                      <div className="infoBlock_news">
                                            <div className="sub">
                                                  <h3>Названия новости</h3>
                                                  <input
                                                          className="newsInp newsInp_name"
                                                          type="text"
                                                          placeholder="Название новости"
                                                          onChange={(e) => setTitle(e.target.value)}/>
                                            </div>
                                      </div>
                                      <div className="infoBlock_news">
                                            <div className="sub">
                                                  <h3>Изображение для новости</h3>
                                                  <input
                                                          className="newsInp newsInp_name"
                                                          type="text"
                                                          placeholder="Изображение для новости"
                                                          onChange={(e) => setImage(e.target.value)}/>
                                            </div>
                                      </div>
                                      <div className="infoBlock_news">
                                            <div className="sub">
                                                  <h3>Дата новости</h3>
                                                  <input
                                                          className="newsInp newsInp_name"
                                                          type="text"
                                                          placeholder="Дата новости"
                                                          onChange={(e) => setDate(e.target.value)}/>
                                            </div>
                                      </div>
                                      <div className="Pod">
                                            <h3>Подробное описание</h3>
                                            <textarea name="description" id="description" className="oPole"
                                                      onChange={(e) => setDescription(e.target.value)}></textarea>
                                      </div>
                                      <button className="news_btn" onClick={() => {
                                            createNews();
                                      }}>Сохранить
                                      </button>
                                </div>
                          </Container>
                    </MainLayout>
              </>

      );
};

export default CreateNews;
