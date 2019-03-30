function modulator(FileName)
x = imread(strcat(FileName,'.jpg'));
Fs = 16000;
currentspot = 1;
[nr,nc]=size(x);
row = 1;
%videoA
for j = 1 : nr
    for i = 1 : 4
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
    end
    counter = 1;
    for i = 5 : 30
        if counter >4
            counter = 1;
        end
        if counter < 3
            y(currentspot,1) = 244;
            
        end
        if counter > 2
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        if counter >4
            counter = 1;
        end
        if counter < 3
            y(currentspot,1) = 244;
            
        end
        if counter > 2
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        if counter >4
            counter = 1;
        end
        if counter < 3
            y(currentspot,1) = 244;
            
        end
        if counter > 2
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        if counter >4
            counter = 1;
        end
        if counter < 3
            y(currentspot,1) = 244;
            
        end
        if counter > 2
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        counter = counter+1;
    end
    for i = 31 : 39
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
    end
    if(mod(row-1,120) == 0)
        for i = 40 : 86
            y(currentspot,1) = 244;
            currentspot = currentspot + 1;
             y(currentspot,1) = 244;
            currentspot = currentspot + 1;
             y(currentspot,1) = 244;
            currentspot = currentspot + 1;
             y(currentspot,1) = 244;
            currentspot = currentspot + 1;
        end
    else
        for i = 40 : 86
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
        end
    end
    for i = 1 : 909
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
    end
    for i = 1 : 45
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
    end
    % video B
    
    for i = 1 : 4
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
    end
    counter = 1;
    for i = 5 : 37
        if counter >5
            counter = 1;
        end
        if counter < 4
            y(currentspot,1) = 244;
            
        end
        if counter > 3
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        if counter >5
            counter = 1;
        end
        if counter < 4
            y(currentspot,1) = 244;
            
        end
        if counter > 3
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        if counter >5
            counter = 1;
        end
        if counter < 4
            y(currentspot,1) = 244;
            
        end
        if counter > 3
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        if counter >5
            counter = 1;
        end
        if counter < 4
            y(currentspot,1) = 244;
            
        end
        if counter > 3
            y(currentspot,1) = 11;
            
        end
        
        currentspot = currentspot + 1;
        counter = counter+1;
    end
    for i = 38 : 39
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
    end
    if(mod(row-1,120) == 0)
        for i = 40 : 86
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
            y(currentspot,1) = 11;
            currentspot = currentspot + 1;
        end
    else
        for i = 40 : 86
            y(currentspot,1) = 244;
            currentspot = currentspot + 1;
            y(currentspot,1) = 244;
            currentspot = currentspot + 1;
            y(currentspot,1) = 244;
            currentspot = currentspot + 1;
            y(currentspot,1) = 244;
            currentspot = currentspot + 1;
        end
    end
    for i = 1 : 909
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
        y(currentspot,1) = x(row,i);
        currentspot = currentspot + 1;
    end
    for i = 1 : 45
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
        y(currentspot,1) = 11;
        currentspot = currentspot + 1;
    end
    row = row +1;

end
% maxVec = max(y);
% minVec = min(y);
% 
% %# normalize to -1...1
% vecN = ((y-minVec)./(maxVec-minVec) - 0.5 ) *2;
%vecN = im2double(y);
[rows,~]=size(y);%# A is your matrix
colMax=max(abs(y),[],1);%# take max absolute value to account for negative numbers
vecN=y./repmat(colMax,rows,1);
t = 1:length(vecN);
t=t';
w = 2*pi*2400/16640;
ted = vecN.*cos(w.*t);

% 
% 
%z = modulate(vecN,2400,16640,'am');%
%z = cos(vecN.*2*pi*2400/16640);
z = resample(ted,16000,16640);


audiowrite(strcat('modulated',FileName,'.wav'),z,16000);
















