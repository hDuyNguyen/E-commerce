import React from 'react'
import MainCarosel from '../../components/HomeCarosel/MainCarosel'
import HomeSectonCarosel from '../../components/HomeSectionCarosel/HomeSectonCarosel'
import { mens_kurta } from '../../../Data/mens_kurta'

const HomePage = () => {
    return (
        <div>
            <MainCarosel />
            <div className='space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10'>
                <HomeSectonCarosel data={mens_kurta} sectionName={"Men's Kurta"} />
                <HomeSectonCarosel sectionName={"Men's Kurta"} />
                <HomeSectonCarosel sectionName={"Men's Shoes"} />
                <HomeSectonCarosel sectionName={"Men's Shirt"} />
                <HomeSectonCarosel sectionName={"Women's Saree"} />
                <HomeSectonCarosel sectionName={"Women's Dress"} />
            </div>
        </div>
    )
}

export default HomePage