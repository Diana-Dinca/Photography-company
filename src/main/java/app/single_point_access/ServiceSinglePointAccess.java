package app.single_point_access;

import app.service.*;
import app.service.implementation.*;
import app.service.performance.AppPerformanceService;
import app.service.performance.SQLProcedurePerformanceService;

public class ServiceSinglePointAccess {

    private static ClientiService clientiService;
    private static AdresaService adresaService;
    private static EvenimenteService evenimenteService;
    private static FotografiService fotografiService;
    private static LocatiiService locatiiService;
    private  static AppPerformanceService appPerformanceService;
    private static SQLProcedurePerformanceService sqlProcedurePerformanceService;
    static {
        clientiService = new ClientiServiceImpl();
        adresaService = new AdresaServiceImpl();
        evenimenteService = new EvenimenteServiceImpl();
        fotografiService = new FotografiServiceImpl();
        locatiiService = new LocatiiServiceImpl();
        appPerformanceService= new AppPerformanceService();
        sqlProcedurePerformanceService= new SQLProcedurePerformanceService();
    }

    public static ClientiService getClientiService() {
        return clientiService;
    }
    public static AdresaService getAdresaService() {
        return adresaService;
    }
    public static EvenimenteService getEvenimenteService() {
        return evenimenteService;
    }
    public static FotografiService getFotografiService() {
        return fotografiService;
    }
    public static LocatiiService getLocatiiService() {
        return locatiiService;
    }
    public static AppPerformanceService getAppPerformanceService(){return appPerformanceService;}
    public static SQLProcedurePerformanceService getSQLProcedurePerformanceService(){return sqlProcedurePerformanceService;}

}
