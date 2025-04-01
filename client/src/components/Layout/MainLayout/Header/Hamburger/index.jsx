import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import React, { useState } from 'react';
import Drawer from "@mui/material/Drawer";
import { Link } from 'react-router-dom';

function Hamburger({ openHamburger, setOpenHamburger, linksLists }) {
    const [toggleForm, setToggleForm] = useState(false);

    function closeHamburger() {
        setOpenHamburger(false);
        setToggleForm(false);
    }

    function LinksComponent({ tittle, url, urls }) {
        return (
            <>
                {url ? (
                    <div>
                        <Link
                            to={url}
                            className="font-semibold text-lg p-1 border-b border-transparent hover:border-[#FF7F2C] hover:text-[#FF7F2C] ease-in-out duration-200"
                        >
                            {tittle}
                        </Link>
                    </div>
                ) : (
                    <>
                        {urls?.map((link) => (
                            <div key={link.id}>
                                <Link
                                    to={link.url}
                                    className="font-semibold text-lg text-center p-1 border-b border-transparent hover:border-[#FF7F2C] hover:text-[#FF7F2C] ease-in-out duration-200"
                                >
                                    {link.tittle}
                                </Link>
                            </div>
                        ))}
                    </>
                )}
            </>
        );
    }

    return (
        <Drawer anchor="right" open={openHamburger} onClose={closeHamburger}>
            <div className="relative w-screen h-screen p-5 pt-[128px]">
                <div className="absolute top-3 right-3">
                    <IconButton aria-label="close" size="large" onClick={closeHamburger}>
                        <CloseIcon sx={{ fontSize: 30 }} />
                    </IconButton>
                </div>
                <div className="h-full w-full">
                    {!toggleForm ? (
                        <div className="h-full flex flex-col justify-between">
                            <div className="grid gap-10 text-center">
                                {linksLists?.map((item) => (
                                    <LinksComponent key={item.id} url={item.url} tittle={item.tittle} urls={item.urls} />
                                ))}
                            </div>
                            <button
                                onClick={() => setToggleForm(true)}
                                className="w-full bg-ourred text-white py-3 px-4 mt-10 rounded font-semibold shadow-lg hover:bg-[#f96807] ease-in-out duration-200"
                            >
                                Войти
                            </button>
                        </div>
                    ) : (
                        <></>
                    )}
                </div>
            </div>
        </Drawer>
    );
}

export default Hamburger;
