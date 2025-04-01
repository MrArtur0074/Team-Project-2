import React, { useEffect, useState } from 'react';
import AdminLayout from '../../../components/Layout/AdminLayout/AdminHeader';
import PieChart from '../../../components/Charts/PieChart';
import { BarChart } from '../../../components/Charts/BarChart';
import AdminSearch from '../../../components/AdminSearch';
import AdminTable from '../../../components/AdminTable';
import AdminCalendar from '../../../components/AdminCalendar';
import axios from 'axios';
import { apiUrl } from '../../../api';

const AdminPanelDonorPage = () => {
    const BloodyData = {
        labels: ['О (I) Rh+', 'A (I) Rh+', 'B (I) Rh+', 'AB (I) Rh+', 'О (I) Rh-', 'A (I) Rh-', 'B (I) Rh-', 'AB (I) Rh-'],
        datasets: [{
            label: '',
            data: [40, 10, 10, 2.5, 6, 7, 6.6, 1.5],
            backgroundColor: ['#63A375', '#E6AF2E', '#7768AE', '#FA8334', '#339989', '#226CE0', '#DA5552'],
            borderColor: ['rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)', 'rgb(201, 203, 207)'],
            borderWidth: 1
        }]
    };

    const [genderData, setGenderData] = useState(null);

    useEffect(() => {
        axios.get(`${apiUrl}/statistics/gender`).then((resp) => {
            const genCount = resp.data.map((dataObj) => dataObj.percent);
            const genTypes = resp.data.map((dataObj) => `${dataObj.gender} - ${dataObj.percent}%`);

            setGenderData({
                labels: genTypes,
                datasets: [{
                    label: '# of Votes',
                    data: genCount,
                    backgroundColor: ['#3A7EE3', '#C53FB7'],
                    borderColor: ['#3A7EE3', '#C53FB7'],
                    borderWidth: 1,
                }],
            });
        });
    }, []);

    return (
        <AdminLayout>
            <div className='container mx-auto p-4'>
                <div className='grid gap-4'>
                    <div className='grid grid-cols-3 gap-4'>
                        <div className='bg-[#D1E5F2] p-4 rounded-lg'>
                            <div className='relative'>
                                <h2 className='text-xl text-[#2A5573]'>Статистика мужчин и женщин</h2>
                                <div className='absolute -left-4 top-[6px] h-4 w-1 bg-[#2A5573]' />
                            </div>
                            <div className='mt-4'>
                                {genderData && <PieChart chartData={genderData} />}
                            </div>
                        </div>
                        <div className='col-span-2 bg-[#D1E5F2] p-4 rounded-lg'>
                            <div className='relative'>
                                <h2 className='text-xl text-[#2A5573]'>Статистика типов крови</h2>
                                <div className='absolute -left-4 top-[6px] h-4 w-1 bg-[#2A5573]' />
                            </div>
                            <div className='mt-4'>
                                <BarChart chartData={BloodyData} />
                            </div>
                        </div>
                    </div>
                    <AdminCalendar />
                    <div className='grid grid-cols-2'>
                        <AdminSearch />
                    </div>
                    <AdminTable />
                </div>
            </div>
        </AdminLayout>
    );
};

export default AdminPanelDonorPage;
