#include <stdio.h>
#include <time.h>
void pointers();
void subscript();
int main(){	
	clock_t start, end;
	double totalTime;
	start = clock();
	int i;
	for(i=0; i<1000000; i++){
		subscript();   
	}
	end=clock();
	totalTime=((double)(end-start))/CLOCKS_PER_SEC;
	printf("Subscript time: %f\n",totalTime);
	start = clock();
	for(i=0; i<1000000; i++){
		pointers();
	}
	end = clock();
	totalTime = ((double) (end - start)) / CLOCKS_PER_SEC;
	printf("Pointers time: %f\n",totalTime);
	  
	return 0;
}
void subscript(){
	int subArray[10][10];
	int i;
	int j;
	for(j=0; j<10 ; j++){
		for(i=0; i<10; i++){
			subArray[i][j]=0;
		}
	}
	return;
}
void pointers(){
	int pointArry[10][10];
	int i;
	int j;
	for(j=0; j<10 ; j++){
		for(i=0; i<10; i++){
			*(*(pointArry+i)+j)=0;
		}
	}
	return;
}


