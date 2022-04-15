import { MainMenu } from "../components/MainMenu"

export const DetailsHotel = () => {
    return (
        <div>
            <MainMenu />
            <section class="py-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center align-items-center">
                    <div class="col">
                        <div class="col-6 col-12">
                            <div class="card mb-4 rounded-3 shadow-sm">
                                <div class="card-header py-3 height-200">
                                    <h4 class="my-0 fw-normal text-center">Hotel São Paulo</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="col-6 col-12">
                            <h2>Quarto de Casal</h2>
                            <label>R$ 60,00</label>
                            <p>
                            Quarto com vista para o mar, com o mais belo por do sol da cidade de São Paulo, temos excelentes serviços de quarto como Limpeza, Lavagem de roupas com secagem rápida, entrega de alimentos no quarto e muito mais.
                            </p>
                        </div>
                    </div>
                </div>
            </section >Ï
        </div>

    )
}