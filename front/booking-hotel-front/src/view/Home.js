import { Header } from "../components/Header"
import { MainMenu } from "../components/MainMenu"
import { SectionProduct } from "../components/SectionProduct"

export const Home = () => { 
    return (
        <div>
            <MainMenu/>
            <Header />
            <SectionProduct /> 
        </div>
    )
}