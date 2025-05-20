import {useEffect, useState} from "react";

import axios from "axios";
import './Account.scss'
import {Link} from "react-router-dom";
import {useAuth} from "../../App/auth/authProvider";
import {Container} from "@mui/material";
import MainLayout from "../../components/Layout/MainLayout";


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

function Account() {
    const auth = useAuth();

    const emptyUserProfile: UserProfile = {
        name: "",
        surname: "",
        dateOfBirth: "",
        bloodType: "",
        gender: ""
    };
    const [activeTab, setActiveTab] = useState('info');
    const [user, setUser] = useState<User | null>(null);

    // Обновление состояния вкладок при изменении хэша
    useEffect(() => {
        window.onhashchange = () => {
            setActiveTab(window.location.hash.substring(1));
        };
    }, []);

    /*  Получение данных пользователя  при входе на страницу*/
    useEffect(() => {
        if(auth?.accessToken){
            getProfile(auth.accessToken).then((data) => {
                setUser(data);
            });
        }
    }, []);



    /**
     * Получение профиля пользователя
     * @param {string} token - Токен доступа пользователя
     */
    async function getProfile(token: string): Promise<User> {
        try {
            const userRequest = await axios.get(`http://127.0.0.1:8080/user-api/profile`, {
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
                        logOut();
                        return { email: "", username: "", profile: emptyUserProfile};
                    }
                }
                else {
                    console.error("An unexpected error occurred");
                    return { email: "", username: "", profile: emptyUserProfile};
                }
            }
            else {
                console.error("An unexpected error occurred");
                return { email: "", username: "", profile: emptyUserProfile};
            }
        }
    }

    /**
     *  Выход из аккаунта */
    function logOut():void{
        if (auth && auth.logOut) {
            auth.logOut();
        }
    }

    /**
     *  хэш URL при изменении вкладки
    * @param {string} tab - Название вкладки
     */
    function handleTabChange(tab: string) {
        setActiveTab(tab);
        window.location.hash = tab;
    };
    /**
     * Обработка изменения ввода данных
     * @param {keyof User} property - Свойство пользователя
     * @param {string | number} value - Значение свойства
     */
    function handleInputChange(property: keyof User, value: string | number) {
        setUser((prevUser) => {
            if (prevUser) {
                return { ...prevUser, [property]: value };
            }
            return prevUser;
        });
        // console.log(user);
    }
    function handleProfileInputChange(property: keyof UserProfile, value: string | number) {
        setUser((prevUser) => {
            if (prevUser) {
                return { ...prevUser, [property]: value };
            }
            return prevUser;
        });
        // console.log(user);
    }


    return (
            <MainLayout>
        <Container className={"myProfile-container"}>
            <div className={"myProfile"}>
                <div className={"myProfile-left"}>

                    <button
                        className={[activeTab === 'info' ? 'active' : '', "myProfile-left__button"].join(' ')}
                        onClick={() => handleTabChange('info')}>
                        <div className={'myProfile-account myProfile-icon'}>Аккаунт</div>
                    </button>

                    <button
                        className={[activeTab === 'data' ? 'active' : '', "myProfile-left__button"].join(' ')}
                        onClick={() => handleTabChange('data')}>
                        <div className={'myProfile-data myProfile-icon'}>Профиль</div>
                    </button>

                </div>
                <div className={"myProfile-right"}>
                    {activeTab === 'info' && <>
                        <div className={"myProfile-right__info"}>
                            <div className={"myProfile-right__info-title"}>
                                Информация об аккаунте
                            </div>
                            <table className={"myProfile-right__info-inputs"}>
                                <tbody>
                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Username</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text" name="username"
                                               value={user?.username || ""} readOnly={true}
                                               onChange={(e) => handleInputChange('username', e.target.value)}></input>
                                    </td>
                                </tr>
                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Email</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text" name="email"
                                               value={user?.email || ""} readOnly={true}
                                               onChange={(e) => handleInputChange('email', e.target.value)}></input>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <button className={"myProfile-right__logout"} onClick={logOut}>Выйти</button>
                    </>}
                    {activeTab === 'data' && <>
                        <div className={"myProfile-right__info"}>
                            <div className={"myProfile-right__info-title"}>
                                Личная информация
                            </div>
                            <table className={"myProfile-right__info-inputs"}>
                                <tbody>
                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Имя</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text" name={"name"}
                                               value={user?.profile?.name || ""} readOnly={true}
                                               onChange={(e) => handleProfileInputChange('name', e.target.value)}></input>
                                    </td>
                                </tr>
                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Фамилия</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text" name={"surname"}
                                               value={user?.profile?.surname || ""} readOnly={true}
                                               onChange={(e) => handleProfileInputChange('surname', e.target.value)}></input>
                                    </td>
                                </tr>
                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Дата рождения</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text"
                                               name={"dateOfBirth"}
                                               value={user?.profile?.dateOfBirth || ""} readOnly={true}
                                               onChange={(e) => handleProfileInputChange('dateOfBirth', e.target.value)}></input>
                                    </td>
                                </tr>

                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Пол</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text"
                                               name={"gender"}
                                               value={user?.profile?.gender || ""} readOnly={true}
                                               onChange={(e) => handleProfileInputChange('gender', e.target.value)}></input>
                                    </td>
                                </tr>

                                <tr className={"myProfile-right__info-input"}>
                                    <td className="myProfile-right__info-column1">Группа крови</td>
                                    <td className="myProfile-right__info-column2">
                                        <input className={"myProfile-right__information"} type="text"
                                               name={"bloodType"}
                                               value={user?.profile?.bloodType || ""} readOnly={true}
                                               onChange={(e) => handleProfileInputChange('bloodType', e.target.value)}></input>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </>}

                    {activeTab === 'tasks' && <>
                        <div className={"myProfile-right__info"}>
                            <div className={"myProfile-right__info-title"}>
                                Tasks
                            </div>
                            <div className="browse-items">
                                <div className="browse-items__bottom">

                                </div>

                            </div>
                        </div>
                    </>}

                    {activeTab === 'groups' && <>
                    <div className={"myProfile-right__info"}>
                            <div className={"myProfile-right__info-title"}>
                                Groups
                            </div>
                            <div className='groups-block'>

                            </div>
                        </div>
                    </>}


                        </div>
                    </div>
                        </Container>
            </MainLayout>
                        );
                    }

                    export default Account;