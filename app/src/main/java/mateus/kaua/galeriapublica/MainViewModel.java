package mateus.kaua.galeriapublica;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import kotlinx.coroutines.CoroutineScope;

/* MainView herdando de android view model */
public class MainViewModel extends AndroidViewModel {

    /* isso possibilita que acessemos o contexto da aplicação dentro do mainView.
     * guardamos a opcao escolhida pelo usuario para q a proxima vez q ele volte
     * o ultimo tipo de lista escolhido seja mostrado*/

    int navigationOpSelected = R.id.gridViewOp;

    LiveData<PagingData<mateus.kaua.galeriapublica.ImageData>> pageLv;

    public MainViewModel(@NonNull Application application){
        super(application);
        GalleryRepository galleryRepository = new GalleryRepository(application);
        GalleryPagingSource galleryPagingSource = new GalleryPagingSource(galleryRepository);
        Pager<Integer, mateus.kaua.galeriapublica.ImageData> pager = new Pager(new PagingConfig(10), () -> galleryPagingSource);
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        pageLv = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }

    public LiveData<PagingData<mateus.kaua.galeriapublica.ImageData>> getPageLv(){
        return pageLv;
    }

    public int getNavigationOpSelected(){
        return navigationOpSelected;
    }

    public void setNavigationOpSelected(int navigationOpSelected){
        this.navigationOpSelected = navigationOpSelected;
    }



}
